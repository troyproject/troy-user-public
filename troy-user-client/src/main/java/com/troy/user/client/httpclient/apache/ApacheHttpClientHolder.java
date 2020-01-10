package com.troy.user.client.httpclient.apache;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName ApacheHttpClientHolder
 * @Description ApacheHttpClient 持有者
 * @date 2017年8月1日 下午2:02:25
 * @history 版本 作者 时间 注释
 */
public class ApacheHttpClientHolder {

    public static ApacheHttpClient getApacheHttpClient() {
        return ApacheHttpClient.getInstance();
    }

    public static ApacheHttpClientProxy getApacheHttpClientProxy() {
        return ApacheHttpClientProxyHolder.INSTANCE;
    }

    private static class ApacheHttpClientProxyHolder {
        private static final ApacheHttpClientProxy INSTANCE = new ApacheHttpClientProxy();
    }

}
