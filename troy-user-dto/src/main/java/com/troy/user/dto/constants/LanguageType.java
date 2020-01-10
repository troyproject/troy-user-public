package com.troy.user.dto.constants;

/**
 * @author Han
 */

public enum LanguageType {

    /**
     * 中文简体
     */
    ZH_CN("ZH_CN"),
    /**
     * 英文
     */
    EN("EN"),
    ;

    private String code;

    LanguageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static LanguageType find(String code) {
        for (LanguageType e : LanguageType.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
