package com.troy.user.dto.constants;

/**
 * 操作类型 1绑定 2解绑
 */
public enum BindOperateTypeEnum {

    BIND(1),
    UNBIND(2);

    private int code;

    BindOperateTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
