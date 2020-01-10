package com.troy.user.dto.constants;

/**
 * @author zhangchengjie
 * @date 2019/07/31
 */
public enum UserBindTypeEnum {

    /** 邮箱 */
    email(1),
    /** 手机号 */
    mobile(2),
    /** 谷歌验证码 */
    google(3);

    private int code;

    UserBindTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
