package com.troy.user.dto.constants;

import com.troy.commons.enums.BaseEnum;

/**
 * 注册渠道
 *
 * @author dp
 */
public enum RegisterSourceEnum implements BaseEnum<String> {

    SOURCE("source", "HEROKUAPP"),
    SOURCE_("source。", "HEROKUAPP"),
    BINANCE("binance", "币安邀请活动");

    private String code;

    private String desc;

    RegisterSourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }
}
