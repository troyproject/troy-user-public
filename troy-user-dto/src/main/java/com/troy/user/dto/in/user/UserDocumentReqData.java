package com.troy.user.dto.in.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.troy.commons.dto.in.ReqData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author zhangchengjie
 * @date 2019/08/02
 */
@Setter
@Getter
public class UserDocumentReqData extends ReqData {

    /**
     * 认证等级 1初级认证 2中级认证 3高级认证
     */
    public static final Integer AUTH_LEVEL_1 = 1;
    /**
     * 认证等级 1初级认证 2中级认证 3高级认证
     */
    public static final Integer AUTH_LEVEL_2 = 2;
    /**
     * 认证等级 1初级认证 2中级认证 3高级认证
     */
    public static final Integer AUTH_LEVEL_3 = 3;

    /**
     * 用户id
     */
    private java.lang.Long userId;
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
     * 国家码
     */
    @NotEmpty(message = "国家码不能为空")
    @ApiModelProperty(value = "国家码")
    private String dialCode;

    /**
     * 中文国家名称（供后台展示使用）
     */
    @NotEmpty(message = "中文国家名称不能为空")
    @ApiModelProperty(value = "中文国家名称")
    private String chCountry;

    /**
     * 名
     */
    @NotEmpty(message = "名不能为空")
    @ApiModelProperty(value = "名")
    private String firstName;

    /**
     * 姓
     */
    @NotEmpty(message = "姓不能为空")
    @ApiModelProperty(value = "姓")
    private String lastName;

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

    @ApiModelProperty(value = "国家id")
    private Long countryId;

}
