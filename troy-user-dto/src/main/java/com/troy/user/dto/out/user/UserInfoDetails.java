package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用系统账户查询响应数据
 *
 * @author sz
 * @date 2018-10-15 11:03
 * @copyright
 */
@Setter
@Getter
public class UserInfoDetails  extends ResData {


    private java.lang.Long userDocumentId;

    /**
     * 用户id
     */
    private java.lang.Long userId;
    /**
     * 等级
     */
    private Integer grade;
    /**
     * 证件类型 （1身份证 2护照 3其他）
     */
    private java.lang.Integer documentType;
    /**
     * 证件姓名
     */
    private java.lang.String documentName;
    /**
     * 证件号码
     */
    private java.lang.String documentNumber;
    /**
     * 证件正面照
     */
    private java.lang.String documentFrontPic;
    /**
     * 证件背面照
     */
    private java.lang.String documentBackPic;
    /**
     * 证件其他照片
     */
    private java.lang.String documentOtherPic;
    /**
     * 身份认证状态 （0未认证 1初级认证中 2初级认证通过 3中级认证中 4中级认证通过 5高级认证中 6高级认证通过）
     */
    private java.lang.Integer documentStatus;

    /**
     * 审核内容
     */
    private java.lang.String remark;

    /**
     * createTime
     */
    protected java.util.Date createTime;

    /**
     * updateTime
     */
    protected java.util.Date updateTime;

    /**
     * 申请时间
     */
    private String applyTime;

    private java.lang.String content;
    private java.lang.String updateBy;
}
