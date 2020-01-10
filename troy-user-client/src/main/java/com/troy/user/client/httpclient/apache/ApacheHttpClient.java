package com.troy.user.client.httpclient.apache;

import com.troy.user.client.httpclient.constants.BodyDataTypeEnum;
import com.troy.user.client.httpclient.constants.Constants;
import com.troy.user.client.httpclient.constants.PropKeyConstants;
import com.troy.user.client.httpclient.converter.NameValuePairConverter;
import com.troy.user.client.httpclient.pair.NameBufferedFilePair;
import com.troy.user.client.httpclient.pair.NameNormalFilePair;
import com.troy.user.client.httpclient.pair.NameStreamedFilePair;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpVersion.HTTP;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ApacheHttpClient
 * @Description apache http 请求客户端
 * @date 2017年7月21日 下午3:40:39
 * @history 版本 作者 时间 注释
 */
@Slf4j
public class ApacheHttpClient {

    private PoolingHttpClientConnectionManager defaultPoolingHttpClientConnectionManager;
    private CloseableHttpClient defaultHttpClient;
    private CloseableHttpClient retryHttpClient;
    private RequestConfig.Builder defaultRequestConfigBuilder = RequestConfig.custom().setConnectionRequestTimeout(Constants.DEFAULT_HTTP_CONNECT_TIMEOUT).setConnectTimeout(Constants.DEFAULT_HTTP_CONNECT_TIMEOUT).setSocketTimeout(Constants.DEFAULT_HTTP_SOCKET_TIMEOUT);

    /**
     * =================== 内部实例化、静态化实例、延迟加载 ===================
     */
    private ApacheHttpClient() {//严禁非内部访问，包括子类
        this.initDefaultPoolingHttpClientConnectionManager();
        this.initDefaultHttpClient();
        this.initRetryHttpClient();
        this.idleConnectionEvictorStart();
    }

    static ApacheHttpClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 初始化连接池管理器
     */
    private void initDefaultPoolingHttpClientConnectionManager() {
        Registry<ConnectionSocketFactory> connectionSocketFactoryRegister = this.createConnectionSocketFactoryRegister();
        this.defaultPoolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(connectionSocketFactoryRegister);
        this.defaultPoolingHttpClientConnectionManager.setMaxTotal(Constants.DEFAULT_HTTP_MAX_TOTAL_CONNECTIONS);
        this.defaultPoolingHttpClientConnectionManager.setDefaultMaxPerRoute(Constants.DEFAULT_HTTP_MAX_PER_ROUTE_CONNECTIONS);
        ConnectionConfig connectionConfig = ConnectionConfig.custom().build();
        this.defaultPoolingHttpClientConnectionManager.setDefaultConnectionConfig(connectionConfig);
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(Constants.DEFAULT_HTTP_SOCKET_TIMEOUT).build();
        this.defaultPoolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
        Thread shutdownThread = new Thread(() -> {
            // TODO Auto-generated method stub
            defaultPoolingHttpClientConnectionManager.shutdown();
        }, Constants.THREAD_NAME_SHUTDOWN_HTTP_CLIENT);
        shutdownThread.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(shutdownThread);
    }
    /**
     * =================== 内部实例化、静态化实例、延迟加载 ===================
     */

    private Registry<ConnectionSocketFactory> createConnectionSocketFactoryRegister() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (x509Certificates, s) -> {
                return true; // 不做身份鉴定
            }).build();
            ConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, Constants.SSL_SUPPORTED_PROTOCOLS, null, NoopHostnameVerifier.INSTANCE);
            return RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, PlainConnectionSocketFactory.INSTANCE)
                    .register(PropKeyConstants.HTTPS, connectionSocketFactory)
                    .build();
        } catch (Exception e) {
            log.error("Create Registry<ConnectionSocketFactory> error", e);
            return null;
        }
    }

    /**
     * 初始化默认的 HTTP 客户端
     */

    private void initDefaultHttpClient() {
        this.defaultHttpClient = HttpClients.custom().setDefaultRequestConfig(this.defaultRequestConfigBuilder.build())
                .setConnectionManager(defaultPoolingHttpClientConnectionManager).build();
    }

    /**
     * 初始化开启重试机制的 HTTP 客户端
     */
    private void initRetryHttpClient() {
        //HttpRequestRetryHandler 是发送请求前连接失败的情况处理
        //ServiceUnavailableRetryStrategy 是发送请求成功后根据返回状态做处理
        this.retryHttpClient = HttpClients.custom().setDefaultRequestConfig(this.defaultRequestConfigBuilder.build())
                .setConnectionManager(defaultPoolingHttpClientConnectionManager).setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy(Constants.DEFAULT_HTTP_RETRY_COUNT, Constants.DEFAULT_HTTP_RETRY_INTERVAL)).setRetryHandler(new CustomHttpRequestRetryHandler()).build();
    }

    /**
     * 开启定时清理无效的http连接
     */
    private void idleConnectionEvictorStart() {
        new IdleConnectionEvictor(defaultPoolingHttpClientConnectionManager);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param outputStream
     * @return http 响应状态
     * @throws IOException
     * @throws URISyntaxException
     */
    public StatusLine downloadFile(String url, OutputStream outputStream) throws IOException, URISyntaxException {
        return this.downloadFile(url, null, outputStream);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param nameValuePairList
     * @param outputStream
     * @return http 响应状态
     * @throws IOException
     * @throws URISyntaxException
     */
    public StatusLine downloadFile(String url, List<NameValuePair> nameValuePairList, OutputStream outputStream) throws IOException, URISyntaxException {
        return this.downloadFile(url, nameValuePairList, null, outputStream);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param nameValuePairList
     * @param requestHeaders    请求头
     * @param outputStream
     * @return http 响应状态
     * @throws IOException
     * @throws URISyntaxException
     */
    public StatusLine downloadFile(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, OutputStream outputStream) throws IOException, URISyntaxException {
        return this.downloadFile(url, nameValuePairList, requestHeaders, null, outputStream);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param nameValuePairList 参数列表
     * @param requestHeaders    请求头
     * @param requestConfig     请求配置（覆盖 defaultRequestConfigBuilder）
     * @param outputStream
     * @return http 响应状态
     * @throws IOException
     * @throws URISyntaxException
     */
    public StatusLine downloadFile(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig, OutputStream outputStream) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.setCharset(Charset.forName(Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING));
        if (nameValuePairList != null && nameValuePairList.size() > 0)
            uriBuilder.setParameters(nameValuePairList);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeaders(requestHeaders);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = this.defaultHttpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK != statusLine.getStatusCode())
                return statusLine;
            response.getEntity().writeTo(outputStream);
            return statusLine;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            log.error("Http request error,url=" + url, e);
            throw e;
        } finally {
            if (response != null)
                response.close();
        }
    }

    /**
     * @param url
     * @param nameStreamedFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传流式的文件
     */
    public String uploadFile(String url, NameStreamedFilePair... nameStreamedFilePairArray) throws IOException {
        return this.uploadFile(url, null, nameStreamedFilePairArray);
    }

    /**
     * @param url
     * @param nameValuePairList         参数列表
     * @param nameStreamedFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传流式的文件
     */
    public String uploadFile(String url, List<NameValuePair> nameValuePairList, NameStreamedFilePair... nameStreamedFilePairArray) throws IOException {
        return this.uploadFile(url, nameValuePairList, null, null, nameStreamedFilePairArray);
    }

    /**
     * @param url
     * @param nameValuePairList         参数列表
     * @param requestHeaders            请求头
     * @param requestConfig             请求配置（覆盖 defaultRequestConfigBuilder）
     * @param nameStreamedFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传流式的文件
     */
    public String uploadFile(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig, NameStreamedFilePair... nameStreamedFilePairArray) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName(Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        if (nameStreamedFilePairArray != null) {
            for (NameStreamedFilePair nameInputStreamPair : nameStreamedFilePairArray) {
                if (nameInputStreamPair == null)
                    continue;
                builder.addBinaryBody(nameInputStreamPair.getName(), nameInputStreamPair.getInputStream(), ContentType.DEFAULT_BINARY, nameInputStreamPair.getFilename());
            }
        }
        this.addTextBody(builder, nameValuePairList);
        httpPost.setEntity(builder.build());
        httpPost.setHeaders(requestHeaders);
        httpPost.setConfig(requestConfig);
        return execute(httpPost);
    }

    /**
     * @param url
     * @param nameBufferedFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传字节形态的文件
     */
    public String uploadFile(String url, NameBufferedFilePair... nameBufferedFilePairArray) throws IOException {
        return this.uploadFile(url, null, nameBufferedFilePairArray);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param nameBufferedFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传字节形态的文件
     */
    public String uploadFile(String url, List<NameValuePair> nameValuePairList, NameBufferedFilePair... nameBufferedFilePairArray) throws IOException {
        return this.uploadFile(url, nameValuePairList, null, null, nameBufferedFilePairArray);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @param requestConfig             请求配置（覆盖 defaultRequestConfigBuilder）
     * @param nameBufferedFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传字节形态的文件
     */
    public String uploadFile(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig, NameBufferedFilePair... nameBufferedFilePairArray) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName(Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        if (nameBufferedFilePairArray != null) {
            for (NameBufferedFilePair nameFileBuffPair : nameBufferedFilePairArray) {
                if (nameFileBuffPair == null)
                    continue;
                builder.addBinaryBody(nameFileBuffPair.getName(), nameFileBuffPair.getFileBuff(), ContentType.DEFAULT_BINARY, nameFileBuffPair.getFilename());
            }
        }
        this.addTextBody(builder, nameValuePairList);
        httpPost.setEntity(builder.build());
        httpPost.setHeaders(requestHeaders);
        httpPost.setConfig(requestConfig);
        return execute(httpPost);
    }

    /**
     * @param url
     * @param nameNormalFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传标准的文件
     */
    public String uploadFile(String url, NameNormalFilePair... nameNormalFilePairArray) throws IOException {
        return this.uploadFile(url, null, nameNormalFilePairArray);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param nameNormalFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传标准的文件
     */
    public String uploadFile(String url, List<NameValuePair> nameValuePairList, NameNormalFilePair... nameNormalFilePairArray) throws IOException {
        return this.uploadFile(url, nameValuePairList, null, null, nameNormalFilePairArray);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @param requestConfig           请求配置（覆盖 defaultRequestConfigBuilder）
     * @param nameNormalFilePairArray
     * @return
     * @throws IOException
     * @author Han
     * @version V1.0.0
     * @Description 上传标准的文件
     */
    public String uploadFile(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig, NameNormalFilePair... nameNormalFilePairArray) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName(Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        if (nameNormalFilePairArray != null && nameNormalFilePairArray.length > 0) {
            for (NameNormalFilePair nameFilePair : nameNormalFilePairArray) {
                if (nameFilePair == null || nameFilePair.getFile() == null)
                    continue;
                builder.addBinaryBody(nameFilePair.getName(), nameFilePair.getFile());
            }
        }
        this.addTextBody(builder, nameValuePairList);
        httpPost.setEntity(builder.build());
        httpPost.setHeaders(requestHeaders);
        httpPost.setConfig(requestConfig);
        return execute(httpPost);
    }

    private void addTextBody(MultipartEntityBuilder builder, List<NameValuePair> nameValuePairList) {
        if (nameValuePairList != null) {
            ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING);
            for (NameValuePair nameValuePair : nameValuePairList) {
                if (nameValuePair == null)
                    continue;
                builder.addPart(nameValuePair.getName(), new StringBody(nameValuePair.getValue(), contentType));
            }
        }
    }

    public String doPost(String url) throws IOException, URISyntaxException {
        return this.doPost(url, new ArrayList<NameValuePair>(0));
    }

    /**
     * @param url
     * @param bodyData（服务端从数据主体中获取值，如下示例：在 spring mvc 环境中）
     *                                     <pre>@RequestMapping("/demoStr")</pre>
     *                                     <pre>public void demoStr(@RequestBody String str) {</pre>
     *
     *                                     <pre>}</pre>
     *
     *                                     <pre>@RequestMapping("/demoJson")</pre>
     *                                     <pre>public void demoJson(@RequestBody String jsonData) {</pre>
     *
     *                                     <pre>}</pre>
     * @param bodyDataTypeEnum             请求头信息
     * @return
     * @throws IOException
     */
    public String doPost(String url, String bodyData, BodyDataTypeEnum bodyDataTypeEnum)
            throws IOException {
        return this.doPost(url, bodyData, bodyDataTypeEnum, null);
    }

    /**
     * @param url
     * @param bodyData（服务端从数据主体中获取值，如下示例：在 spring mvc 环境中）
     *                                     <pre>@RequestMapping("/demoStr")</pre>
     *                                     <pre>public void demoStr(@RequestBody String str) {</pre>
     *
     *                                     <pre>}</pre>
     *
     *                                     <pre>@RequestMapping("/demoJson")</pre>
     *                                     <pre>public void demoJson(@RequestBody String jsonData) {</pre>
     *
     *                                     <pre>}</pre>
     * @param bodyDataTypeEnum             数据类型
     * @param requestConfig                请求配置（覆盖 defaultRequestConfigBuilder）
     * @return
     * @throws IOException
     */
    public String doPost(String url, String bodyData, BodyDataTypeEnum bodyDataTypeEnum, RequestConfig requestConfig)
            throws IOException {
        return this.doPost(url, bodyData, bodyDataTypeEnum, requestConfig, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * @param url
     * @param bodyData（服务端从数据主体中获取值，如下示例：在 spring mvc 环境中）
     *                                     <pre>@RequestMapping("/demoStr")</pre>
     *                                     <pre>public void demoStr(@RequestBody String str) {</pre>
     *
     *                                     <pre>}</pre>
     *
     *                                     <pre>@RequestMapping("/demoJson")</pre>
     *                                     <pre>public void demoJson(@RequestBody String jsonData) {</pre>
     *
     *                                     <pre>}</pre>
     * @param bodyDataTypeEnum             数据类型
     * @param requestConfig                请求配置（覆盖 defaultRequestConfigBuilder）
     * @param retryEnable                  是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @throws IOException
     */
    public String doPost(String url, String bodyData, BodyDataTypeEnum bodyDataTypeEnum, RequestConfig requestConfig, boolean retryEnable)
            throws IOException {
        return this.doPost(url, bodyData, new Header[]{bodyDataTypeEnum.getHeader()}, requestConfig, retryEnable);
    }

    /**
     * @param url
     * @param bodyData（服务端从数据主体中获取值，如下示例：在 spring mvc 环境中）
     *                                     <pre>@RequestMapping("/demoStr")</pre>
     *                                     <pre>public void demoStr(@RequestBody String str) {</pre>
     *
     *                                     <pre>}</pre>
     *
     *                                     <pre>@RequestMapping("/demoJson")</pre>
     *                                     <pre>public void demoJson(@RequestBody String jsonData) {</pre>
     *
     *                                     <pre>}</pre>
     * @param requestHeaders               请求头信息
     * @return
     * @throws IOException
     */
    public String doPost(String url, String bodyData, Header[] requestHeaders)
            throws IOException {
        return this.doPost(url, bodyData, requestHeaders, null);
    }

    /**
     * @param url
     * @param bodyData（服务端从数据主体中获取值，如下示例：在 spring mvc 环境中）
     *                                     <pre>@RequestMapping("/demoStr")</pre>
     *                                     <pre>public void demoStr(@RequestBody String str) {</pre>
     *
     *                                     <pre>}</pre>
     *
     *                                     <pre>@RequestMapping("/demoJson")</pre>
     *                                     <pre>public void demoJson(@RequestBody String jsonData) {</pre>
     *
     *                                     <pre>}</pre>
     * @param requestHeaders               请求头信息
     * @param requestConfig                请求配置（覆盖 defaultRequestConfigBuilder）
     * @return
     * @throws IOException
     */
    public String doPost(String url, String bodyData, Header[] requestHeaders, RequestConfig requestConfig)
            throws IOException {
        return this.doPost(url, bodyData, requestHeaders, requestConfig, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * @param url
     * @param bodyData（服务端从数据主体中获取值，如下示例：在 spring mvc 环境中）
     *                                     <pre>@RequestMapping("/demoStr")</pre>
     *                                     <pre>public void demoStr(@RequestBody String str) {</pre>
     *
     *                                     <pre>}</pre>
     *
     *                                     <pre>@RequestMapping("/demoJson")</pre>
     *                                     <pre>public void demoJson(@RequestBody String jsonData) {</pre>
     *
     *                                     <pre>}</pre>
     * @param requestHeaders               请求头信息
     * @param requestConfig                请求配置（覆盖 defaultRequestConfigBuilder）
     * @param retryEnable                  是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @throws IOException
     */
    public String doPost(String url, String bodyData, Header[] requestHeaders, RequestConfig requestConfig, boolean retryEnable)
            throws IOException {
        StringEntity stringEntity = new StringEntity(bodyData, Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(stringEntity);
        httpPost.setHeaders(requestHeaders);
        httpPost.setConfig(requestConfig);
        return this.execute(httpPost, retryEnable);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, List<NameValuePair> nameValuePairList)
            throws URISyntaxException, IOException {
        return this.doPost(url, nameValuePairList, null);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders)
            throws URISyntaxException, IOException {
        return this.doPost(url, nameValuePairList, requestHeaders, null);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @param requestConfig     请求配置（覆盖 defaultRequestConfigBuilder）
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig)
            throws URISyntaxException, IOException {
        return this.doPost(url, nameValuePairList, requestHeaders, requestConfig, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @param requestConfig     请求配置（覆盖 defaultRequestConfigBuilder）
     * @param retryEnable       是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig, boolean retryEnable)
            throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost(url);
        if (nameValuePairList != null && nameValuePairList.size() > 0) {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList,
                    Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING);
            httpPost.setEntity(urlEncodedFormEntity);
        }
        httpPost.setHeaders(requestHeaders);
        httpPost.setConfig(requestConfig);
        return this.execute(httpPost, retryEnable);
    }

    /**
     * @param url
     * @param parameters
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, Map<String, String> parameters)
            throws URISyntaxException, IOException {
        return this.doPost(url, parameters, null);
    }

    /**
     * @param url
     * @param parameters
     * @param requestHeaders
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, Map<String, String> parameters, Header[] requestHeaders)
            throws URISyntaxException, IOException {
        return this.doPost(url, parameters, requestHeaders, null);
    }

    /**
     * @param url
     * @param parameters
     * @param requestHeaders
     * @param requestConfig  请求配置（覆盖 defaultRequestConfigBuilder）
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, Map<String, String> parameters, Header[] requestHeaders, RequestConfig requestConfig)
            throws URISyntaxException, IOException {
        return this.doPost(url, parameters, requestHeaders, requestConfig, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * @param url
     * @param parameters
     * @param requestHeaders
     * @param requestConfig  请求配置（覆盖 defaultRequestConfigBuilder）
     * @param retryEnable    是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doPost(String url, Map<String, String> parameters, Header[] requestHeaders, RequestConfig requestConfig, boolean retryEnable)
            throws URISyntaxException, IOException {
        List<NameValuePair> nameValuePairList = NameValuePairConverter.convert(parameters);
        return this.doPost(url, nameValuePairList, requestHeaders, requestConfig, retryEnable);
    }

    /**
     * @param url
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doGet(String url) throws IOException, URISyntaxException {
        return this.doGet(url, new ArrayList<NameValuePair>(0));
    }

    /**
     * @param url
     * @param nameValuePairList
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, List<NameValuePair> nameValuePairList)
            throws URISyntaxException, IOException {
        return this.doGet(url, nameValuePairList, null);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders)
            throws URISyntaxException, IOException {
        return this.doGet(url, nameValuePairList, requestHeaders, null);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @param requestConfig     请求配置（覆盖 defaultRequestConfigBuilder）
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig)
            throws URISyntaxException, IOException {
        return this.doGet(url, nameValuePairList, requestHeaders, requestConfig, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * @param url
     * @param nameValuePairList
     * @param requestHeaders
     * @param requestConfig     请求配置（覆盖 defaultRequestConfigBuilder）
     * @param retryEnable       是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, List<NameValuePair> nameValuePairList, Header[] requestHeaders, RequestConfig requestConfig, boolean retryEnable)
            throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.setCharset(Charset.forName(Constants.DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING));
        if (nameValuePairList != null && nameValuePairList.size() > 0)
            uriBuilder.setParameters(nameValuePairList);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeaders(requestHeaders);
        httpGet.setConfig(requestConfig);
        return this.execute(httpGet, retryEnable);
    }

    /**
     * @param url
     * @param parameters
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String, String> parameters)
            throws URISyntaxException, IOException {
        return this.doGet(url, parameters, null);
    }

    /**
     * @param url
     * @param parameters
     * @param requestHeaders
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String, String> parameters, Header[] requestHeaders)
            throws URISyntaxException, IOException {
        return this.doGet(url, parameters, requestHeaders, null);
    }

    /**
     * @param url
     * @param parameters
     * @param requestHeaders
     * @param requestConfig  请求配置（覆盖 defaultRequestConfigBuilder）
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String, String> parameters, Header[] requestHeaders, RequestConfig requestConfig)
            throws URISyntaxException, IOException {
        return this.doGet(url, parameters, requestHeaders, requestConfig, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * @param url
     * @param parameters
     * @param requestHeaders
     * @param requestConfig  请求配置（覆盖 defaultRequestConfigBuilder）
     * @param retryEnable    是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public String doGet(String url, Map<String, String> parameters, Header[] requestHeaders, RequestConfig requestConfig, boolean retryEnable)
            throws URISyntaxException, IOException {
        List<NameValuePair> nameValuePairList = NameValuePairConverter.convert(parameters);
        return this.doGet(url, nameValuePairList, requestHeaders, requestConfig, retryEnable);
    }

    /**
     * 发送 HTTP 请求
     *
     * @param request
     * @return
     * @throws IOException
     */
    public String execute(HttpRequestBase request) throws IOException {
        return this.execute(request, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * 发送 HTTP 请求
     *
     * @param request
     * @param retryEnable 是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @throws IOException
     */
    public String execute(HttpRequestBase request, boolean retryEnable) throws IOException {
        long startTime = System.currentTimeMillis();
        CloseableHttpResponse response = null;
        try {
            response = retryEnable ? this.retryHttpClient.execute(request) : this.defaultHttpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity == null)
                return null;
            log.info("Request duration(Millisecond)= {}", (System.currentTimeMillis() - startTime));
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(String.format("Http request error,duration(Millisecond)=%s,request=%s,config=%s", (System.currentTimeMillis() - startTime), request, request.getConfig()), e);
            throw e;
        } finally {
            if (response != null)
                response.close();
        }
    }

    public RequestConfig.Builder getDefaultRequestConfigBuilder() {
        return defaultRequestConfigBuilder;
    }

    private static class SingletonHolder {
        private static final ApacheHttpClient INSTANCE = new ApacheHttpClient();
    }

    /**
     * 发送 HTTP 请求前连接失败的情况处理
     */
    class CustomHttpRequestRetryHandler implements HttpRequestRetryHandler {

        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount > Constants.DEFAULT_HTTP_RETRY_COUNT) {
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                return true;
            }
            if (exception instanceof ConnectionPoolTimeoutException) {
                return true;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }
            return false;
        }

    }

}