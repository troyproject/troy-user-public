package com.troy.user.web.constants;

/**
 * 发送验证码
 */
public enum FlagEnum {
    /**
     * 首次发
     */
    FIRST(0),
    /**
     * 重新发
     */
    RETRY(1);

    private Integer code;

    FlagEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
