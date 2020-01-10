package com.troy.user.client.httpclient.apache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.user.client.httpclient.constants.BodyDataTypeEnum;
import com.troy.user.client.httpclient.constants.Constants;
import com.troy.user.client.httpclient.pair.NameBufferedFilePair;
import com.troy.user.client.httpclient.pair.NameNormalFilePair;
import com.troy.user.client.httpclient.pair.NameStreamedFilePair;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ApacheHttpClientProxy
 * @Description 代理 apache http client
 * @date 2017年8月1日 上午10:17:01
 * @history 版本 作者 时间 注释
 */
public class ApacheHttpClientProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheHttpClientProxy.class);

    ApacheHttpClientProxy() {

    }

    /**
     * @param url           请求地址
     * @param req           请求参数（body data）
     * @param typeReference 出参主体实际类型
     * @return
     * @Title post
     * @Description POST 请求，示例：
     * <pre>
     *  Res<UploadFileOut> res = HttpClientApi.getApacheHttpClientProxy().doPost(url, in, new TypeReference<Res<UploadFileOut>>() {});
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO/* 出参主体实际类型 */ extends ResData> Res<RDO> doPost(String url, Req<? extends ReqData> req,
                                                               TypeReference<Res<RDO>> typeReference) {
        // TODO Auto-generated method stub
        return this.doPost(url, req, typeReference, new Header[0]);
    }

    /**
     * @param url           请求地址
     * @param req           请求参数（body data）
     * @param typeReference 出参主体实际类型
     * @param token         令牌
     * @return
     * @Title post
     * @Description POST 请求，示例：
     * <pre>
     *  Res<UploadFileOut> res = HttpClientApi.getApacheHttpClientProxy().doPost(url, in, new TypeReference<Res<UploadFileOut>>() {});
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO/* 出参主体实际类型 */ extends ResData> Res<RDO> doPost(String url, Req<? extends ReqData> req,
                                                               TypeReference<Res<RDO>> typeReference, String token) {
        // TODO Auto-generated method stub
        Header authorizationHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, String.format(Constants.PATTERN_HEADER_TOKEN_VALUE, token));
        return this.doPost(url, req, typeReference, new Header[]{authorizationHeader});
    }

    /**
     * @param url           请求地址
     * @param req           请求参数（body data）
     * @param typeReference 出参主体实际类型
     * @param headers       请求头信息
     * @return
     * @Title post
     * @Description POST 请求，示例：
     * <pre>
     *  Res<UploadFileOut> res = HttpClientApi.getApacheHttpClientProxy().doPost(url, in, new TypeReference<Res<UploadFileOut>>() {}, headers);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO/* 出参主体实际类型 */ extends ResData> Res<RDO> doPost(String url, Req<? extends ReqData> req,
                                                               TypeReference<Res<RDO>> typeReference, Header[] headers) {
        // TODO Auto-generated method stub
        return this.doPost(url, req, typeReference, headers, null);
    }

    /**
     * @param url           请求地址
     * @param req           请求参数（body data）
     * @param typeReference 出参主体实际类型
     * @param headers       请求头信息
     * @param requestConfig 请求配置（覆盖 defaultRequestConfigBuilder）
     * @return
     * @Title post
     * @Description POST 请求，示例：
     * <pre>
     *  Res<UploadFileOut> res = HttpClientApi.getApacheHttpClientProxy().doPost(url, in, new TypeReference<Res<UploadFileOut>>() {}, headers, requestConfig);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO/* 出参主体实际类型 */ extends ResData> Res<RDO> doPost(String url, Req<? extends ReqData> req,
                                                               TypeReference<Res<RDO>> typeReference, Header[] headers, RequestConfig requestConfig) {
        // TODO Auto-generated method stub
        return this.doPost(url, req, typeReference, headers, requestConfig, Constants.DEFAULT_HTTP_RETRY_ENABLE);
    }

    /**
     * @param url           请求地址
     * @param req           请求参数（body data）
     * @param typeReference 出参主体实际类型
     * @param headers       请求头信息
     * @param requestConfig 请求配置（覆盖默认配置）
     * @param retryEnable   是否启用失败重试，谨慎使用，特别是请求非幂等服务
     * @return
     * @Title post
     * @Description POST 请求，示例：
     * <pre>
     *  Res<UploadFileOut> res = HttpClientApi.getApacheHttpClientProxy().doPost(url, in, new TypeReference<Res<UploadFileOut>>() {}, headers, requestConfig, retryEnable);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO/* 出参主体实际类型 */ extends ResData> Res<RDO> doPost(String url, Req<? extends ReqData> req,
                                                               TypeReference<Res<RDO>> typeReference, Header[] headers, RequestConfig requestConfig, boolean retryEnable) {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(url) || req == null)
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_PARAMETER);
        String jsonReq = JSON.toJSONString(req);
        //添加必要的请求头
        headers = headers == null ? new Header[0] : headers;
        Header[] newHeaders = new Header[headers.length + 1];
        System.arraycopy(headers, 0, newHeaders, 0, headers.length);
        newHeaders[newHeaders.length - 1] = BodyDataTypeEnum.JSON.getHeader();//设置数据类型
        String jsonRes = null;
        try {
            jsonRes = ApacheHttpClient.getInstance().doPost(url, jsonReq, newHeaders, requestConfig, retryEnable);
            Res<RDO> res = JSON.parseObject(jsonRes, typeReference);
            if (res == null || res.getHead() == null)
                return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_UNEXPECTED_RESULTS, jsonRes);
            return res;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error(String.format("Request error,url=%s,jsonReq=%s,jsonRes=%s,headers=%s,requestConfig=%s", url, jsonReq, jsonRes, Arrays.asList(headers), requestConfig), e);
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, e.getMessage());
        }
    }

    /**
     * @param url                     请求地址
     * @param typeReference           出参主体实际类型
     * @param nameNormalFilePairArray 标准文件实体数组
     * @return
     * @Description 上传标准文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, nameNormalFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameNormalFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, TypeReference<Res<RDO>> typeReference, NameNormalFilePair... nameNormalFilePairArray) {
        return this.uploadFile(url, null, typeReference, nameNormalFilePairArray);
    }

    /**
     * @param url                     请求地址
     * @param nameValuePairList       请求参数
     * @param typeReference           出参主体实际类型
     * @param nameNormalFilePairArray 标准文件实体数组
     * @return
     * @Description 上传标准文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, nameNormalFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameNormalFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, NameNormalFilePair... nameNormalFilePairArray) {
        return this.uploadFile(url, nameValuePairList, typeReference, new Header[0], nameNormalFilePairArray);
    }

    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, String token, NameNormalFilePair... nameNormalFilePairArray) {
        Header authorizationHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, String.format(Constants.PATTERN_HEADER_TOKEN_VALUE, token));
        return this.uploadFile(url, nameValuePairList, typeReference, new Header[]{authorizationHeader}, nameNormalFilePairArray);
    }

    /**
     * @param url                     请求地址
     * @param nameValuePairList       请求参数
     * @param typeReference           出参主体实际类型
     * @param headers                 请求头信息
     * @param nameNormalFilePairArray 标准文件实体数组
     * @return
     * @Description 上传标准文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, headers, nameNormalFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameValuePairList, nameNormalFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, Header[] headers, NameNormalFilePair... nameNormalFilePairArray) {
        return this.uploadFile(url, nameValuePairList, typeReference, headers, null, nameNormalFilePairArray);
    }

    /**
     * @param url                     请求地址
     * @param nameValuePairList       请求参数
     * @param typeReference           出参主体实际类型
     * @param headers                 请求头信息
     * @param requestConfig           请求配置（覆盖默认配置）
     * @param nameNormalFilePairArray 标准文件实体数组
     * @return
     * @Description 上传标准文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, headers, requestConfig, nameNormalFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameValuePairList, requestConfig, nameNormalFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, Header[] headers, RequestConfig requestConfig, NameNormalFilePair... nameNormalFilePairArray) {
        if (StringUtils.isEmpty(url) || nameNormalFilePairArray == null || nameNormalFilePairArray.length == 0)
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_PARAMETER);
        String jsonRes = null;
        try {
            jsonRes = ApacheHttpClient.getInstance().uploadFile(url, nameValuePairList, headers, requestConfig, nameNormalFilePairArray);
            Res<RDO> res = JSON.parseObject(jsonRes, typeReference);
            if (res == null || res.getHead() == null)
                return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_UNEXPECTED_RESULTS, jsonRes);
            return res;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error(String.format("Upload normal file error,url=%s,nameValuePairList=%s,newHeaders=%s,requestConfig=%s,jsonRes=%s", url, nameValuePairList, Arrays.asList(headers), requestConfig, jsonRes), e);
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, e.getMessage());
        }
    }

    /**
     * @param url                       请求地址
     * @param typeReference             出参主体实际类型
     * @param nameBufferedFilePairArray 字节形态的文件实体数组
     * @return
     * @Description 上传字节形态的文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, nameBufferedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameBufferedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, TypeReference<Res<RDO>> typeReference, NameBufferedFilePair... nameBufferedFilePairArray) {
        return this.uploadFile(url, null, typeReference, nameBufferedFilePairArray);
    }

    /**
     * @param url                       请求地址
     * @param nameValuePairList         请求参数
     * @param typeReference             出参主体实际类型
     * @param nameBufferedFilePairArray 字节形态的文件实体数组
     * @return
     * @Description 上传字节形态的文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, nameBufferedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameBufferedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, NameBufferedFilePair... nameBufferedFilePairArray) {
        return this.uploadFile(url, nameValuePairList, typeReference, null, nameBufferedFilePairArray);
    }

    /**
     * @param url                       请求地址
     * @param nameValuePairList         请求参数
     * @param typeReference             出参主体实际类型
     * @param headers                   请求头信息
     * @param nameBufferedFilePairArray 字节形态的文件实体数组
     * @return
     * @Description 上传字节形态的文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, headers, nameBufferedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameValuePairList, nameBufferedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, Header[] headers, NameBufferedFilePair... nameBufferedFilePairArray) {
        return this.uploadFile(url, nameValuePairList, typeReference, headers, null, nameBufferedFilePairArray);
    }

    /**
     * @param url                       请求地址
     * @param nameValuePairList         请求参数
     * @param typeReference             出参主体实际类型
     * @param headers                   请求头信息
     * @param requestConfig             请求配置（覆盖默认配置）
     * @param nameBufferedFilePairArray 字节形态的文件实体数组
     * @return
     * @Description 上传字节形态的文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, headers, requestConfig, nameBufferedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameValuePairList, requestConfig, nameBufferedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, Header[] headers, RequestConfig requestConfig, NameBufferedFilePair... nameBufferedFilePairArray) {
        if (StringUtils.isEmpty(url) || nameBufferedFilePairArray == null || nameBufferedFilePairArray.length == 0)
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_PARAMETER);
        String jsonRes = null;
        try {
            jsonRes = ApacheHttpClient.getInstance().uploadFile(url, nameValuePairList, headers, requestConfig, nameBufferedFilePairArray);
            Res<RDO> res = JSON.parseObject(jsonRes, typeReference);
            if (res == null || res.getHead() == null)
                return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_UNEXPECTED_RESULTS, jsonRes);
            return res;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error(String.format("Upload buffered file error,url=%s,nameValuePairList=%s,newHeaders=%s,requestConfig=%s,jsonRes=%s", url, nameValuePairList, Arrays.asList(headers), requestConfig, jsonRes), e);
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, e.getMessage());
        }
    }

    /**
     * @param url                       请求地址
     * @param typeReference             出参主体实际类型
     * @param nameStreamedFilePairArray 流式文件实体数组
     * @return
     * @Description 上传流式文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, nameStreamedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameStreamedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, TypeReference<Res<RDO>> typeReference, NameStreamedFilePair... nameStreamedFilePairArray) {
        return this.uploadFile(url, null, typeReference, nameStreamedFilePairArray);
    }

    /**
     * @param url                       请求地址
     * @param nameValuePairList         请求参数
     * @param typeReference             出参主体实际类型
     * @param nameStreamedFilePairArray 流式文件实体数组
     * @return
     * @Description 上传流式文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, nameStreamedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameStreamedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, NameStreamedFilePair... nameStreamedFilePairArray) {
        return this.uploadFile(url, nameValuePairList, typeReference, null, nameStreamedFilePairArray);
    }

    /**
     * @param url                       请求地址
     * @param nameValuePairList         请求参数
     * @param typeReference             出参主体实际类型
     * @param headers                   请求头信息
     * @param nameStreamedFilePairArray 流式文件实体数组
     * @return
     * @Description 上传流式文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, headers, nameStreamedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameValuePairList, nameStreamedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, Header[] headers, NameStreamedFilePair... nameStreamedFilePairArray) {
        return this.uploadFile(url, nameValuePairList, typeReference, headers, null, nameStreamedFilePairArray);
    }

    /**
     * @param url                       请求地址
     * @param nameValuePairList         请求参数
     * @param typeReference             出参主体实际类型
     * @param headers                   请求头信息
     * @param requestConfig             请求配置（覆盖默认配置）
     * @param nameStreamedFilePairArray 流式文件实体数组
     * @return
     * @Description 上传流式文件，示例：
     * <p>
     * <pre>
     *     单文件：
     *      Res<UploadFileForSingleResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForSingleResData>>() {}, headers, requestConfig, nameStreamedFilePair);
     *     多文件：
     *      Res<UploadFileForBatchResData> res = CommonHttpClientFactory.getDefaultCommonHttpClient().uploadFile(url, nameValuePairList,
     *          new TypeReference<Res<UploadFileForBatchResData>>() {}, nameValuePairList, requestConfig, nameStreamedFilePairArray);
     * </pre>
     * @author Han
     * @date 2017年8月1日 上午10:47:34
     * @version V1.0.0
     * @history 版本 作者 时间 注释
     */
    public <RDO extends ResData> Res<RDO> uploadFile(String url, List<NameValuePair> nameValuePairList, TypeReference<Res<RDO>> typeReference, Header[] headers, RequestConfig requestConfig, NameStreamedFilePair... nameStreamedFilePairArray) {
        if (StringUtils.isEmpty(url) || nameStreamedFilePairArray == null || nameStreamedFilePairArray.length == 0)
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_PARAMETER);
        String jsonRes = null;
        try {
            jsonRes = ApacheHttpClient.getInstance().uploadFile(url, nameValuePairList, headers, requestConfig, nameStreamedFilePairArray);
            Res<RDO> res = JSON.parseObject(jsonRes, typeReference);
            if (res == null || res.getHead() == null)
                return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_UNEXPECTED_RESULTS, jsonRes);
            return res;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error(String.format("Upload streamed file error,url=%s,nameValuePairList=%s,headers=%s,requestConfig=%s,jsonRes=%s", url, nameValuePairList, Arrays.asList(headers), requestConfig, jsonRes), e);
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, e.getMessage());
        }
    }

    /**
     * 下载文件
     *
     * @param url
     * @param outputStream
     * @return
     */
    public Res<ResData> downloadFile(String url, OutputStream outputStream) {
        return this.downloadFile(url, null, outputStream);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param nameValuePairList
     * @param outputStream
     * @return
     */
    public Res<ResData> downloadFile(String url, List<NameValuePair> nameValuePairList, OutputStream outputStream) {
        return this.downloadFile(url, nameValuePairList, null, outputStream);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param nameValuePairList
     * @param headers           请求头信息
     * @param outputStream
     * @return
     */
    public Res<ResData> downloadFile(String url, List<NameValuePair> nameValuePairList, Header[] headers, OutputStream outputStream) {
        return this.downloadFile(url, nameValuePairList, headers, null, outputStream);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param nameValuePairList
     * @param headers           请求头信息
     * @param requestConfig     请求配置（覆盖默认配置）
     * @param outputStream
     * @return
     */
    public Res<ResData> downloadFile(String url, List<NameValuePair> nameValuePairList, Header[] headers, RequestConfig requestConfig, OutputStream outputStream) {
        if (StringUtils.isEmpty(url) || outputStream == null)
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_PARAMETER);
        try {
            StatusLine statusLine = ApacheHttpClient.getInstance().downloadFile(url, nameValuePairList, headers, requestConfig, outputStream);
            if (HttpStatus.SC_NOT_FOUND == statusLine.getStatusCode() || HttpStatus.SC_UNAUTHORIZED == statusLine.getStatusCode())
                return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_PARAMETER, statusLine.toString());
            if (HttpStatus.SC_OK != statusLine.getStatusCode())
                return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, statusLine.toString());
            return ResFactory.getInstance().createRes();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LOGGER.error(String.format("Download file error,url=%s,nameValuePairList=%s,headers=%s,requestConfig=%s", url, nameValuePairList, Arrays.asList(headers), requestConfig), e);
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT, e.getMessage());
        }
    }
}
