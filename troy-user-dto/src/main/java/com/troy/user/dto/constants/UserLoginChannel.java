package com.troy.user.dto.constants;

/**
 * description 登录渠道
 *
 * @author Han
 * @date 2018/10/8 10:08
 */
public enum UserLoginChannel {

    WEB(1),
    ANDROID(2),
    IOS(3);

    private int code;

    UserLoginChannel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserLoginChannel find(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserLoginChannel e : UserLoginChannel.values()) {
            if (e.getCode() == code.intValue()) {
                return e;
            }
        }
        return null;
    }
}
