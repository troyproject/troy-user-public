package com.troy.user.service.internal.user;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 毫秒为单位httpclient
 *
 * @author demon
 * @date 2016年7月18日
 */
public class HttpClientHisdataService {
	private static final Logger logger = Logger.getLogger(HttpClientHisdataService.class);
	private static final String CHARSET = "UTF-8";

	public static HttpClientHisdataService httpClientService() {
		HttpClientHisdataService service = new HttpClientHisdataService();
		return service;
	}

	public HttpClientHisdataService() {
	}

	public CloseableHttpClient createBasicHttpClient() {

		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 1) {
					// 不进行httpclient里面的重试，到外面去重试
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {
					// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {
					// ssl握手异常
					return false;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求是幂等的，就再次尝试
					return true;
				}
				return false;
			}

		};

		return HttpClients.custom().setRetryHandler(myRetryHandler)
				.setConnectionManager(new BasicHttpClientConnectionManager()).build();
	}



	public static Map<String, String> httpPostMultipart(String url, List<MultipartFile> multipartFiles, String fileParName,
														Map<String, Object> params, int timeout) {
		Map<String, String> resultMap = new HashMap<>();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		try {
			HttpPost httpPost = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setCharset(Charset.forName("UTF-8"));
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			String fileName = null;
			MultipartFile multipartFile = null;
			for (int i = 0; i < multipartFiles.size(); i++) {
				multipartFile = multipartFiles.get(i);
				fileName = multipartFile.getOriginalFilename();
				builder.addBinaryBody(fileParName, multipartFile.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
			}
			//决中文乱码
			ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if(entry.getValue() == null)
					continue;
				// 类似浏览器表单提交，对应input的name和value
				builder.addTextBody(entry.getKey(), entry.getValue().toString(), contentType);
			}
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);// 执行提交

			// 设置连接超时时间
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
					.setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
			httpPost.setConfig(requestConfig);

			HttpEntity responseEntity = response.getEntity();
			resultMap.put("scode", String.valueOf(response.getStatusLine().getStatusCode()));
			resultMap.put("data", "");
			if (responseEntity != null) {
				// 将响应内容转换为字符串
				result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			resultMap.put("scode", "error");
			resultMap.put("data", "HTTP请求出现异常: " + e.getMessage());

			Writer w = new StringWriter();
			e.printStackTrace(new PrintWriter(w));
			logger.error("HTTP请求出现异常: " + w.toString());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	public static String postFileMultiPart(String url,Map<String,ContentBody> reqParam, Map<String,String> headers) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			// 创建httpget.
			HttpPost httppost = new HttpPost(url);

			//setConnectTimeout：设置连接超时时间，单位毫秒。setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
			RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(15000).build();
			httppost.setConfig(defaultRequestConfig);

			System.out.println("executing request " + httppost.getURI());

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			for(Map.Entry<String,ContentBody> param : reqParam.entrySet()){
				multipartEntityBuilder.addPart(param.getKey(), param.getValue());
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();
			httppost.setEntity(reqEntity);
			if (headers != null) {
				for (final Map.Entry<String, String> entry : headers.entrySet()) {
					httppost.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
				}
			}
			// 执行post请求.
			CloseableHttpResponse response = httpclient.execute(httppost);

			System.out.println("got response");

			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				// 打印响应状态
				System.out.println(response.getStatusLine());
				if (entity != null) {
					return EntityUtils.toString(entity,Charset.forName("UTF-8"));
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();

			}
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 *
	 * @param header
	 *            :post请求数据header信息
	 * @param values
	 *            :post请求数据
	 * @param connectionRequestTimeout
	 *            单位（s）
	 * @param connectTimeout
	 * @param socketTimeout
	 * @param charset
	 *            字符编码
	 * @param httpMethod 支持PUT和POST
	 * @return
	 */
	public HttpResult httpPost(String url, int connectionRequestTimeout, int connectTimeout, int socketTimeout,
			Map<String, String> headers, Map<String, String> values, Charset charsetRequest, String charsetReadResponse,
			boolean ifhttps, String httpMethod) {
		HttpResult httpResult = new HttpResult();
		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = null;
		HttpEntityEnclosingRequestBase httpPost = null;
		CloseableHttpClient httpClient = null;
		try {
				logger.info("--> [请求] url  = " + url + "\tvalues  = " + values);
			if (null == url || url.length() == 0) {
				logger.info("url is null");
				httpResult.setStatus(-500);
				return httpResult;
			}

			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).setCookieSpec(CookieSpecs.STANDARD).build();
			// 创建cookie store的本地实例
			CookieStore cookieStore = new BasicCookieStore();
			// 创建HttpClient上下文
			HttpClientContext context = HttpClientContext.create();
			context.setCookieStore(cookieStore);

			if("put".equalsIgnoreCase(httpMethod)){
				httpPost=new HttpPut(url);
			}else{
				httpPost = new HttpPost(url);
			}
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			httpPost.setConfig(config);
			// 添加请求消息头
			if (headers != null) {
				for (final Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
				}
			}

			// 添加请求消息体
			if (values != null) {
				NameValuePair namePair;
				for (final Map.Entry<String, String> entry : values.entrySet()) {
					namePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					params.add(namePair);
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
						null == charsetRequest ? Consts.UTF_8 : charsetRequest);
				httpPost.setEntity(entity);
			}

			httpClient = createBasicHttpClient();
//			httpClient= HttpClients.custom().setDefaultRequestConfig(config) //带有cookie的
//					.setDefaultCookieStore(cookieStore).build();
			if (ifhttps == true) {
				httpClient = createSSLClientDefault();
			}

			response = httpClient.execute(httpPost, new BasicHttpContext());


			int status = response.getStatusLine().getStatusCode();
			httpResult.setStatus(status);
			//if (HttpStatus.SC_OK == status) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					httpResult.setResposeString(EntityUtils.toString(entity,
							StringUtils.isEmpty(charsetReadResponse) ? CHARSET : charsetReadResponse));
				}
				// 确保inputStream 已经读取完成，inputStream close会触发链接的释放
				EntityUtils.consume(entity);
			//}
			logger.info("--> [响应] url  = " + url + "\tvalues  = " + httpResult.getResposeString() + "\tstatus = "
					+ status + "\t耗时=" + (System.currentTimeMillis() - startTime) + "ms");
		} catch (ClientProtocolException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} catch (IOException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpResponse出错", e);
				}
			}

			if (null != httpPost) {
				httpPost.abort();
				httpPost.releaseConnection();
			}

			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("关闭httpClient出错", e);
				}
			}
		}

		return httpResult;
	}



	/**
	 * 获取指定接口的cookie
	 * @param header
	 *            :post请求数据header信息
	 * @param values
	 *            :post请求数据
	 * @param connectionRequestTimeout
	 *            单位（s）
	 * @param connectTimeout
	 * @param socketTimeout
	 * @param charset
	 *            字符编码
	 * @param httpMethod 支持PUT和POST
	 * @return
	 */
	public HttpResult httpPostCookie(String url, int connectionRequestTimeout, int connectTimeout, int socketTimeout,
							   Map<String, String> headers, Map<String, String> values, Charset charsetRequest, String charsetReadResponse,
							   boolean ifhttps, String httpMethod) {
		HttpResult httpResult = new HttpResult();
		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = null;
		HttpEntityEnclosingRequestBase httpPost = null;
		CloseableHttpClient httpClient = null;
		try {
			logger.info("--> [请求] url  = " + url + "\tvalues  = " + values);
			if (null == url || url.length() == 0) {
				logger.info("url is null");
				httpResult.setStatus(-500);
				return httpResult;
			}

			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).setCookieSpec(CookieSpecs.STANDARD).build();
			// 创建cookie store的本地实例
			CookieStore cookieStore = new BasicCookieStore();
			// 创建HttpClient上下文
			HttpClientContext context = HttpClientContext.create();
			context.setCookieStore(cookieStore);

			if("put".equalsIgnoreCase(httpMethod)){
				httpPost=new HttpPut(url);
			}else{
				httpPost = new HttpPost(url);
			}
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			httpPost.setConfig(config);
			// 添加请求消息头
			if (headers != null) {
				for (final Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
				}
			}

			// 添加请求消息体
			if (values != null) {
				NameValuePair namePair;
				for (final Map.Entry<String, String> entry : values.entrySet()) {
					namePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					params.add(namePair);
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
						null == charsetRequest ? Consts.UTF_8 : charsetRequest);
				httpPost.setEntity(entity);
			}

//			httpClient = createBasicHttpClient();
			httpClient= HttpClients.custom().setDefaultRequestConfig(config) //带有cookie的
					.setDefaultCookieStore(cookieStore).build();
			if (ifhttps == true) {
				httpClient = createSSLClientDefault();
			}

			response = httpClient.execute(httpPost, new BasicHttpContext());

			System.out.println("访问cms登录接口的获取的常规Cookie:===============");
			for (Cookie c : cookieStore.getCookies()) {
				System.out.println(c.getName() + ": " + c.getValue());
			}

			int status = response.getStatusLine().getStatusCode();
			httpResult.setStatus(status);
			//if (HttpStatus.SC_OK == status) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				httpResult.setResposeString(EntityUtils.toString(entity,
						StringUtils.isEmpty(charsetReadResponse) ? CHARSET : charsetReadResponse));
			}
			// 确保inputStream 已经读取完成，inputStream close会触发链接的释放
			EntityUtils.consume(entity);
			//}
			logger.info("--> [响应] url  = " + url + "\tvalues  = " + httpResult.getResposeString() + "\tstatus = "
					+ status + "\t耗时=" + (System.currentTimeMillis() - startTime) + "ms");
		} catch (ClientProtocolException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} catch (IOException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpResponse出错", e);
				}
			}

			if (null != httpPost) {
				httpPost.abort();
				httpPost.releaseConnection();
			}

			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("关闭httpClient出错", e);
				}
			}
		}

		return httpResult;
	}





	public HttpResult httpPostStringEntity(String url, int connectionRequestTimeout, int connectTimeout,
			int socketTimeout, Map<String, String> headers, String values, Charset charsetRequest,
			String charsetReadResponse, boolean ifhttps) throws UnsupportedEncodingException {
		HttpResult httpResult = new HttpResult();
		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;

		try {

			logger.info("--> [请求]url  = " + url + "\tvalues  = " + values);
			if (null == url || url.length() == 0) {
				logger.info("url is null");
				httpResult.setStatus(-500);
				return httpResult;
			}

			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			httpPost = new HttpPost(url);

			httpPost.setConfig(config);
			// 添加请求消息头
			if (headers != null) {
				for (final Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
				}
			}

			StringEntity reEntity = new StringEntity(values,
					charsetRequest == null ? Consts.UTF_8.name() : charsetRequest.name());
			httpPost.setEntity(reEntity);
			httpClient = createBasicHttpClient();
			if (ifhttps == true) {
				httpClient = createSSLClientDefault();
			}
			response = httpClient.execute(httpPost, new BasicHttpContext());
			int status = response.getStatusLine().getStatusCode();
			httpResult.setStatus(status);

			logger.info("--> [响应] url  = " + url + "\tvalues  = " + values + "\tstatus = " + status + "\t耗时="
					+ (System.currentTimeMillis() - startTime) + "ms");
			// if (HttpStatus.SC_OK == status) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				httpResult.setResposeString(EntityUtils.toString(entity,
						StringUtils.isEmpty(charsetReadResponse) ? CHARSET : charsetReadResponse));
			}
			// 确保inputStream 已经读取完成，inputStream close会触发链接的释放
			EntityUtils.consume(entity);
			// }
			//logger.info("得到的响应的结果是" + httpResult.getResposeString());
		} catch (ClientProtocolException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} catch (IOException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpResponse出错", e);
				}
			}

			if (null != httpPost) {
				httpPost.releaseConnection();
			}

			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("关闭httpClient出错", e);
				}
			}
		}

		return httpResult;
	}

	/**
	 *
	 * @param header
	 *            :post请求数据header信息
	 * @param values
	 *            :post请求数据
	 * @param connectionRequestTimeout
	 *            单位（s）
	 * @param connectTimeout
	 * @param socketTimeout
	 * @param charset
	 *            字符编码
	 * @return
	 */
	public HttpResult httpPost(String url, Map<String, String> values) {
		HttpResult httpResult = new HttpResult();
		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		try {
			if ("null".equals(values.get("pic"))) {
				logger.info("--> [请求] url  = " + url + "\tvalues  = " + values);
			}
			if (null == url || url.length() == 0) {
				logger.info("url is null");
				httpResult.setStatus(-500);
				return httpResult;
			}
			logger.info("--> [请求] url  = " + url + "\tvalues  = " + values);
			int connectionRequestTimeout = 6000;
			int connectTimeout = 6000;
			int socketTimeout = 6000;
			Charset charsetRequest = null;
			String charsetReadResponse = "UTF-8";

			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();

			httpPost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			httpPost.setConfig(config);

			// 添加请求消息体
			if (values != null) {
				NameValuePair namePair;
				for (final Map.Entry<String, String> entry : values.entrySet()) {
					namePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					params.add(namePair);
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
						null == charsetRequest ? Consts.UTF_8 : charsetRequest);
				httpPost.setEntity(entity);
			}

			httpClient = createBasicHttpClient();

			response = httpClient.execute(httpPost, new BasicHttpContext());
			int status = response.getStatusLine().getStatusCode();
			httpResult.setStatus(status);
			// if (HttpStatus.SC_OK == status) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				httpResult.setResposeString(EntityUtils.toString(entity,
						StringUtils.isEmpty(charsetReadResponse) ? CHARSET : charsetReadResponse));
			}
			// 确保inputStream 已经读取完成，inputStream close会触发链接的释放
			EntityUtils.consume(entity);
			// }
			logger.info("得到的响应的结果是" + httpResult.getResposeString() + "\tstatus = " + status + "\t耗时="
					+ (System.currentTimeMillis() - startTime) + "ms");
		} catch (ClientProtocolException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} catch (IOException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpResponse出错", e);
				}
			}

			if (null != httpPost) {
				httpPost.abort();
				httpPost.releaseConnection();
			}

			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("关闭httpClient出错", e);
				}
			}
		}

		return httpResult;
	}

	/**
	 *
	 * @param header
	 *            :get请求数据header信息
	 * @param values
	 *            :get请求数据
	 * @param connectionRequestTimeout
	 *            单位（s）
	 * @param connectTimeout
	 * @param socketTimeout
	 * @param charset
	 *            字符编码
	 * @return
	 */
	public HttpResult httpGet(String url, Map<String, String> values, Map<String, String> headers) {
		HttpResult httpResult = new HttpResult();
		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = null;
		HttpGet httpGet = null;
		CloseableHttpClient httpClient = null;
		try {
			//logger.info("--> [请求] url  = " + url + "\tvalues  = " + values);
			if (null == url || url.length() == 0) {
				logger.info("url is null");
				httpResult.setStatus(-500);
				return httpResult;
			}

/*			int connectionRequestTimeout = 10000;
			int connectTimeout = 10000;
			int socketTimeout = 10000;*/
			int connectionRequestTimeout = 0;
			int connectTimeout = 0;
			int socketTimeout = 0;
			Charset charsetRequest = null;
			String charsetReadResponse = "UTF-8";

			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();

			httpGet = new HttpGet(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			httpGet.setConfig(config);

			// 添加请求消息头
			if (headers != null) {
				for (final Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
				}
			}

			// 添加请求消息体
			if (values != null) {
				NameValuePair namePair;
				for (final Map.Entry<String, String> entry : values.entrySet()) {
					namePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					params.add(namePair);
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
						null == charsetRequest ? Consts.UTF_8 : charsetRequest);
				((HttpEntityEnclosingRequest) httpGet).setEntity(entity);
			}

			httpClient = createBasicHttpClient();

			response = httpClient.execute(httpGet, new BasicHttpContext());
			int status = response.getStatusLine().getStatusCode();
			httpResult.setStatus(status);
			// if (HttpStatus.SC_OK == status) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				httpResult.setResposeString(EntityUtils.toString(entity,
						StringUtils.isEmpty(charsetReadResponse) ? CHARSET : charsetReadResponse));
			}
			// 确保inputStream 已经读取完成，inputStream close会触发链接的释放
			EntityUtils.consume(entity);
			// }
			logger.info("得到的响应的结果是" + httpResult.getResposeString() + "\tstatus = " + status + "\t耗时="
					+ (System.currentTimeMillis() - startTime) + "ms");
		} catch (ClientProtocolException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpGet) {
				httpGet.abort();
			}
		} catch (IOException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpGet) {
				httpGet.abort();
			}
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpResponse出错", e);
				}
			}

			if (null != httpGet) {
				httpGet.abort();
				httpGet.releaseConnection();
			}

			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("关闭httpClient出错", e);
				}
			}
		}

		return httpResult;
	}

	public HttpResult httpPostStringEntity(String url, String values) {
		HttpResult httpResult = new HttpResult();
		long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = null;
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		try {

			// logger.info("--> [请求]url = " + url + "\tvalues = " + values);
			if (null == url || url.length() == 0) {
				logger.info("url is null");
				httpResult.setStatus(-500);
				return httpResult;
			}
			int connectionRequestTimeout = 10000;
			int connectTimeout = 10000;
			int socketTimeout = 10000;
			Charset charsetRequest = null;
			String charsetReadResponse = "UTF-8";

			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
			httpPost = new HttpPost(url);

			httpPost.setConfig(config);

			StringEntity reEntity = new StringEntity(values,
					charsetRequest == null ? Consts.UTF_8.name() : charsetRequest.name());
			reEntity.setContentEncoding(CHARSET);
			httpPost.setEntity(reEntity);

			httpClient = createBasicHttpClient();

			response = httpClient.execute(httpPost, new BasicHttpContext());
			int status = response.getStatusLine().getStatusCode();
			httpResult.setStatus(status);

			logger.info("--> [响应] url  = " + url + "\tvalues  = " + values + "\tstatus = " + status + "\t耗时="
					+ (System.currentTimeMillis() - startTime) + "ms");
			// if (HttpStatus.SC_OK == status) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				httpResult.setResposeString(EntityUtils.toString(entity,
						StringUtils.isEmpty(charsetReadResponse) ? CHARSET : charsetReadResponse));
			}
			// 确保inputStream 已经读取完成，inputStream close会触发链接的释放
			EntityUtils.consume(entity);
			// }
			logger.info("得到的响应的结果是" + httpResult.getResposeString() + "\tstatus = " + status + "\t耗时="
					+ (System.currentTimeMillis() - startTime) + "ms");
		} catch (ClientProtocolException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} catch (IOException e) {
			logger.error("--> [Exception] url = " + url);
			logger.error("--> [Exception] data = " + values);
			logger.error("--> [Exception] 耗时 = " + (System.currentTimeMillis() - startTime) + " 毫秒");
			logger.error(e, e);
			if (null != httpPost) {
				httpPost.abort();
			}
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpResponse出错", e);
				}
			}

			if (null != httpPost) {
				httpPost.releaseConnection();
			}

			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("关闭httpClient出错", e);
				}
			}
		}

		return httpResult;
	}

	/*
	 * 通过GET方式发起https请求
	 */
	public HttpResult httpsGet(String url, String values) {
		HttpResult httpResult = new HttpResult();
		long startTime = System.currentTimeMillis();
		try {
			// 创建默认的httpsClient实例
			CloseableHttpClient httpsClient = createSSLClientDefault();

			// 用get方法发送http请求
			String requestUrl = url + values;
			HttpGet get = new HttpGet(requestUrl);
			logger.info("执行get请求:...." + get.getURI());

			CloseableHttpResponse httpResponse = null;
			// 发送get请求
			httpResponse = httpsClient.execute(get);
			int status = httpResponse.getStatusLine().getStatusCode();
			httpResult.setStatus(status);

			logger.info("--> [响应] url  = " + url + "\tvalues = " + values + "\tstatus = " + status + "\t耗时="
					+ (System.currentTimeMillis() - startTime) + "ms");
			try {
				// response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					logger.info("响应状态码:" + httpResponse.getStatusLine());
					logger.info("-------------------------------------------------");
					httpResult.setResposeString(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
					logger.info("得到的响应的结果是" + httpResult.getResposeString() + "\tstatus = " + status + "\t耗时="
							+ (System.currentTimeMillis() - startTime) + "ms");
				}
			} finally {
				httpResponse.close();
			}
			if (httpsClient != null) {
				httpsClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
		return httpResult;
	}

	/*
	 * 通过POST方式发起https请求
	 */
	public HttpResult httpsPost(String url, Map<String, String> values) {
		HttpResult httpResult = new HttpResult();
		CloseableHttpClient httpClient = createSSLClientDefault();
		try {
			HttpPost post = new HttpPost(url); // 这里用上本机的某个工程做测试
			// 创建参数列表
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			// 添加请求消息体
			if (values != null) {
				NameValuePair namePair;
				for (final Map.Entry<String, String> entry : values.entrySet()) {
					namePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					list.add(namePair);
				}
			}

			// url格式编码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);
			logger.info("POST 请求...." + post.getURI());
			// 执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					logger.info("-------------------------------------------------------");
					logger.info(EntityUtils.toString(uefEntity));
					logger.info("-------------------------------------------------------");
				}
			} finally {
				httpResponse.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return httpResult;
	}

	public static HttpResult postIo(String urlString, String params) {

		try {
			HttpResult httpResult = new HttpResult();
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(1000 * 600); // 设置连接主机超时（单位：毫秒）
			conn.setReadTimeout(1000 * 600); // 设置从主机读取数据超时（单位：毫秒）
			conn.getOutputStream().write(params.getBytes("utf8"));
			conn.getOutputStream().flush();
			conn.getOutputStream().close();

			InputStream in = conn.getInputStream();
			byte[] buffer = new byte[in.available()];
			StringBuffer stringBuffer = new StringBuffer();

			int httpCode = conn.getResponseCode();
			logger.info("返回的httpCode状态码是" + httpCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String s = "";
			while ((s = br.readLine()) != null) {
				stringBuffer.append(s);
			}
			in.close();
			httpResult.setStatus(httpCode);
			httpResult.setResposeString(stringBuffer.toString());
			return httpResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("http请求失败" + e + "请求地址是" + urlString);
			logger.error(e, e);
		}

		return null;

	}

	// 用于进行Https请求的HttpClient
	public static CloseableHttpClient createSSLClientDefault() {

		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				@Override
				public boolean isTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
					// TODO Auto-generated method stub
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建ssl证书时出错", e);
		}

		return HttpClients.createDefault();
	}

}