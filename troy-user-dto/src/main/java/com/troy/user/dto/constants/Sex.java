package com.troy.user.dto.constants;

/**
 * description 性别
 *
 * @author Han
 * @date 2018/10/8 10:08
 */
public enum Sex {

    /**
     * 保密
     */
    SECRECY(0),
    MEN(1),
    WOMEN(2);

    private int code;

    Sex(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Sex find(Integer code) {
        if (code == null) {
            return null;
        }
        for (Sex e : Sex.values()) {
            if (e.getCode() == code.intValue()) {
                return e;
            }
        }
        return null;
    }
}
