package com.troy.user.dto.constants;

/**
 * 发送方式 1邮箱 2手机 3谷歌
 */
public enum SendWayEnum {

    EMAIL(1),
    MOBILE(2),
    GOOGLE(3);

    private int code;

    SendWayEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
