package com.troy.user.web.constants;

/**
 * description 定义常量
 *
 * @author Han
 * @date 2018/9/26 16:14
 */
public class Constants {

    public static final String ACCESS_EXPRESSION_TOKEN_KEY = "permitAll()";
    public static final String ACCESS_EXPRESSION_CHECK_TOKEN = "isAuthenticated()";

    public static final String DEFAULT_URL_OAUTH_NORMAL_TOKEN = "/oauth/token";
    public static final String DEFAULT_URL_OAUTH_NORMAL_CHECK_TOKEN = "/oauth/check_token";
    public static final String DEFAULT_URL_OAUTH_NORMAL_TOKEN_KEY = "/oauth/token_key";
}
