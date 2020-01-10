package com.troy.user.dto.constants;

/**
 * description 授权模式
 *
 * @author Han
 * @date 2018-10-24 16:48
 */
public enum RegisterType {

    PHONE("PHONE"),
    EMAIL("EMAIL"),
    ;

    private String code;

    RegisterType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RegisterType find(String code) {
        if (code == null) {
            return null;
        }
        for (RegisterType e : RegisterType.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
