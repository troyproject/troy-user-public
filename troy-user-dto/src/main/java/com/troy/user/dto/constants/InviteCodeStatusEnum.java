package com.troy.user.dto.constants;

/**
 * description
 * 邀请码表
 * @author
 * @date 2019-10-21 16:48
 */
public enum InviteCodeStatusEnum {

    USE(0),
    USED(1),
    ;

    private Integer code;

    InviteCodeStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static InviteCodeStatusEnum find(Integer code) {
        if (code == null) {
            return null;
        }
        for (InviteCodeStatusEnum e : InviteCodeStatusEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
