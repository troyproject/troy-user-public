package com.troy.user.client.constants;

/**
 * description 定义 client 模块常量
 *
 * @author Han
 * @date 2018/9/27 10:04
 */
public class Constants {

    public static final String PATTERN_URL = "http://%s:%s%s";

    public static final String DEFAULT_SCOPE = "default";

    public static final int DEFAULT_SCHEDULER_SERVER_PORT = 80;

    /**
     * 基本认证用户名密码
     */
    public static final String PATTERN_BASIC_AUTH_SECRET = "%s:%s";

    /**
     * thread name
     */
    public static final String THREAD_NAME_EVICT_EXPIRY_TOKEN = "user-evict-expiry-token";

    /**
     * Token 缓存到期阀值，Token 实际到期时间 - CACHE_TOKEN_EXPIRES_THRESHOLD_MILLISECONDS 作为 Token 失效标准
     */
    public static final int CACHE_TOKEN_EXPIRES_THRESHOLD_MILLISECONDS = 1000 * 60 * 5;
    /**
     * token 缓存空间最大长度，缓存空间
     */
    public static final int CACHE_TOKEN_MAX_SIZE = 500;

    public static final String DEFAULT_EMPTY_TOKEN = "null";
}
