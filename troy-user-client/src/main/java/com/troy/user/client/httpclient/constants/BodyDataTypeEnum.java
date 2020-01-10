package com.troy.user.client.httpclient.constants;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

/**
 * @author Han
 * @version V1.0.0
 * @ClassName BodyDataTypeEnum
 * @Description 数据主体类型枚举
 * @date 2017年7月25日 下午3:18:20
 * @history 版本 作者 时间 注释
 */
public enum BodyDataTypeEnum {

    TEXT("text/plain; charset=UTF-8"), JSON("application/json; charset=UTF-8"), MULTIPART("multipart/form-data; charset=UTF-8");

    private Header header;

    private BodyDataTypeEnum(String value) {
        this.header = new BasicHeader(HTTP.CONTENT_TYPE, value);
    }

    /**
     * @return header TODO（描述 header 所表达的含义）
     * @Title getHeader
     * @Description 获取 header TODO（描述 header 所表达的含义）
     */
    public Header getHeader() {
        return header;
    }

}
