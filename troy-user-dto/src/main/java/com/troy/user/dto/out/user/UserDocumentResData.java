package com.troy.user.dto.out.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.troy.commons.dto.out.ResData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhangchengjie
 * @date 2019/08/15
 */
@Setter
@Getter
public class UserDocumentResData extends ResData {
    /**
     * 用户认证id
     */
    @ApiModelProperty(value = "用户认证id")
    @JsonSerialize(using = ToStringSerializer.class)
    private java.lang.Long userDocumentId;
    /**
     * 证件类型 （1身份证 2护照 3其他）
     */
    @ApiModelProperty(value = "证件类型 （1身份证 2护照 3其他）")
    private java.lang.Integer documentType;
    /**
     * 证件姓名
     */
    @ApiModelProperty(value = "证件姓名")
    private java.lang.String documentName;
    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private java.lang.String documentNumber;
    /**
     * 证件正面照
     */
    @ApiModelProperty(value = "证件正面照")
    private java.lang.String documentFrontPic;
    /**
     * 证件背面照
     */
    @ApiModelProperty(value = "证件背面照")
    private java.lang.String documentBackPic;
    /**
     * 证件其他照片
     */
    @ApiModelProperty(value = "证件其他照片")
    private java.lang.String documentOtherPic;
    /**
     * 认证等级 1初级认证 2中级认证 3高级认证
     */
    @ApiModelProperty(value = "认证等级 1初级认证 2中级认证 3高级认证")
    private java.lang.Integer authLevel;

    /**
     * 身份认证状态 （0未认证 1初级认证中 2初级认证通过 3中级认证中 4中级认证通过 5高级认证中 6高级认证通过）
     */
    @ApiModelProperty(value = "身份认证状态 （0未认证 1初级认证中 2初级认证通过 3中级认证中 4中级认证通过 5高级认证中 6高级认证通过）")
    private java.lang.Integer documentStatus;

    /**
     * 审核内容
     */
    private String remark;


    /**
     * 审核内容
     */
    private String content;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 国家编码
     */
    private String countryCode;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

}
