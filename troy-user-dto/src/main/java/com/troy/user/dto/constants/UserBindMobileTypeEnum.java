package com.troy.user.dto.constants;

/**
 * @author caq
 * @date 2019/10/10
 */
public enum UserBindMobileTypeEnum {

    /** 绑定 */
    BIND(1),
    /** 非绑定 */
    NOBIND(0);

    private int code;

    UserBindMobileTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
