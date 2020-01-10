package com.troy.user.client.httpclient.constants;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName Constants
 * @Description commons-http-client 配置
 * @date 2017年7月21日 上午11:28:10
 * @history 版本 作者 时间 注释
 */
public class Constants {

    public static final String THREAD_NAME_SHUTDOWN_HTTP_CLIENT = "http-client-manager-shutdown";

    /**
     * 每个路由最大连接数
     */
    public static final int DEFAULT_HTTP_MAX_PER_ROUTE_CONNECTIONS = 500;
    /**
     * 最大连接数
     */
    public static final int DEFAULT_HTTP_MAX_TOTAL_CONNECTIONS = 2 * DEFAULT_HTTP_MAX_PER_ROUTE_CONNECTIONS;
    /**
     * 连接超时时间
     */
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 1000 * 3;
    /**
     * Socket 超时时间
     */
    public static final int DEFAULT_HTTP_SOCKET_TIMEOUT = 1000 * 20;

    public static final boolean DEFAULT_HTTP_RETRY_ENABLE = false;

    /**
     * HTTP 请求重试次数
     */
    public static final int DEFAULT_HTTP_RETRY_COUNT = 1;
    /**
     * HTTP 请求重试间隔毫秒数
     */
    public static final int DEFAULT_HTTP_RETRY_INTERVAL = 500;
    /**
     * 默认消息体编码
     */
    public static final String DEFAULT_HTTP_DEFAULT_CONTENT_ENCODING = "UTF-8";

    public static final String[] SSL_SUPPORTED_PROTOCOLS = {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"};

    public static final String PATTERN_HEADER_TOKEN_VALUE = "Bearer %s";
}
