package com.troy.user.dto.constants;

/**
 * description 授权模式
 *
 * @author Han
 * @date 2018-10-24 16:48
 */
public enum GrantType {

    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE("authorization_code"),
    /**
     * 客户端模式
     */
    CLIENT_CREDENTIALS("client_credentials"),
    /**
     * 授权码模式的简易实现
     */
    IMPLICIT("implicit"),
    /**
     * 刷新令牌模式
     */
    REFRESH_TOKEN("refresh_token"),
    /**
     * 密码模式
     */
    PASSWORD("password");

    private String code;

    GrantType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static GrantType find(String code) {
        if (code == null) {
            return null;
        }
        for (GrantType e : GrantType.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
