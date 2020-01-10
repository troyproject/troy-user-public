package com.troy.user.dao.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * description 数据模型基本模板
 *
 * @author Han
 * @date 2018/9/28 11:14
 */
@Setter
@Getter
public abstract class BaseModel {

    /**
     * createBy
     */
    protected String createBy;
    /**
     * createTime
     */
    protected java.util.Date createTime;
    /**
     * updateBy
     */
    protected String updateBy;
    /**
     * updateTime
     */
    protected java.util.Date updateTime;
    /**
     * lastUpdateFromIp
     */
    protected String lastUpdateFromIp;

}
