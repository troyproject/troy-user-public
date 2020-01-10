package com.troy.user.dto.constants;

/**
 * description 定义常量（名称）
 *
 * @author Han
 * @date 2018/9/26 16:13
 */
public interface KeyConstants {

    /**
     * request token parameter name
     */
    String REQUEST_TOKEN_PARAMETER_REFRESH_TOKEN = "refresh_token";

    /**
     * response token parameter name
     */
    String RESPONSE_TOKEN_KEY_ALG = "alg";
    String RESPONSE_TOKEN_KEY_VALUE = "value";

    /**
     * request、response auth parameter name
     */
    String AUTHENTICATION_USER_ID = "userId";
    String AUTHENTICATION_USERNAME = "username";
    String AUTHENTICATION_PASSWORD = "password";
    String AUTHENTICATION_VERIFICATION_CODE = "verificationCode";
    String AUTHENTICATION_IP = "ip";
    String AUTHENTICATION_CHANNEL = "channel";
}
