package com.troy.user.dto.constants;

/**
 * description
 *
 * @author Han
 * @date 2018-10-24 16:48
 */
public enum NotifyChannel {

    SMS("SMS"),
    EMAIL("EMAIL"),
    ;

    private String code;

    NotifyChannel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static NotifyChannel find(String code) {
        if (code == null) {
            return null;
        }
        for (NotifyChannel e : NotifyChannel.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
