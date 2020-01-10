package com.troy.user.client.httpclient;

import com.troy.user.client.httpclient.apache.ApacheHttpClient;
import com.troy.user.client.httpclient.apache.ApacheHttpClientHolder;
import com.troy.user.client.httpclient.apache.ApacheHttpClientProxy;

/**
 * http 客户端 API
 *
 * @author Han
 */
public class HttpClientApi {

    /**
     * 返回 apache http client
     *
     * @return
     */
    public static ApacheHttpClient getApacheHttpClient() {
        return ApacheHttpClientHolder.getApacheHttpClient();
    }

    /**
     * 返回代理 apache http client,适用于内部 http 服务通信协议
     *
     * @return
     */
    public static ApacheHttpClientProxy getApacheHttpClientProxy() {
        return ApacheHttpClientHolder.getApacheHttpClientProxy();
    }
}
