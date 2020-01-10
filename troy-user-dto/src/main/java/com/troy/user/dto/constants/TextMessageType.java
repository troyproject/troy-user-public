package com.troy.user.dto.constants;

/**
 * @author Han
 */

public enum TextMessageType {

    /**
     * 用户注册
     */
    REGISTER("REGISTER"),
    /**
     * 用户登录
     */
    LOGIN("LOGIN"),
    /**
     * 用户修改密码
     */
    MODIFY_PASSWORD("MODIFY_PASSWORD"),
    /**
     * 用户找回密码
     */
    RESET_PASSWORD("RESET_PASSWORD"),
    /**
     * 用户安全项绑定--手机
     */
    USER_EMAIL_SAFE_BIND("USER_SAFE_BIND"),
    /**
     * 用户安全项绑定--谷歌
     */
    USER_GOOGLE_SAFE_BIND("USER_GOOGLE_SAFE_BIND"),
    /**
     * 用户安全绑定
     */
    USER_SAFE_BIND("USER_SAFE_BIND"),
    /**
     * 用户手机安全解绑
     */
    USER_PHONE_SAFE_UNBIND("USER_PHONE_SAFE_UNBIND"),
    /**
     * 用户谷歌验证码安全解绑
     */
    USER_GOOGLE_SAFE_UNBIND("USER_GOOGLE_SAFE_UNBIND"),
    /**
     * 提现
     */
    WITHDRAW("WITHDRAW"),

    /**
     * 用户绑定风控
     */
    RISK_CONTROL("RISK_CONTROL")
    ;

    private String code;


    TextMessageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static TextMessageType find(String code) {
        for (TextMessageType e : TextMessageType.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
