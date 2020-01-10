package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户个人中心基本信息
 *
 * @author zhangchengjie
 * @date 2019/07/30
 */
@Setter
@Getter
public class UserInfo extends ResData {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 证件姓名
     */
    @ApiModelProperty(value = "证件名称")
    private String documentName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 身份认证状态 （0未认证 1认证中 2已认证）
     */
    @ApiModelProperty(value = "身份认证状态 （0未认证 1初级认证中 2初级认证通过 3中级认证中 4中级认证通过 5高级认证中 6高级认证通过）")
    private Integer documentStatus;
    /**
     * 身份认证类型 1身份证 2护照
     */
    @ApiModelProperty(value = "身份认证类型 1身份证 2护照")
    private Integer documentType;
    /**
     * 安全等级 （1低 2中 3高）
     */
    @ApiModelProperty(value = "安全等级 （1低 2中 3高）")
    private Integer safeLevel;
    /**
     * 是否绑定email（0未绑定 1绑定）
     */
    @ApiModelProperty(value = "是否绑定email（0未绑定 1绑定）")
    private Integer bindEmail;
    /**
     * 是否绑定手机 （0未绑定 1绑定）
     */
    @ApiModelProperty(value = "是否绑定手机 （0未绑定 1绑定）")
    private Integer bindMobile;
    /**
     * 是否绑定谷歌验证码 （0未绑定 1绑定）
     */
    @ApiModelProperty(value = "是否绑定谷歌验证码 （0未绑定 1绑定）")
    private Integer bindGoogleCode;
    /**
     * 交易状态（0不可以 1可以）
     */
    private Integer canTrade;

    /**
     * 资产划转（0不可以 1可以）
     */
    private Integer canTrans;

    /**
     * 提币权限0不可以 1可以）
     */
    private Integer canWithdraw;

    /**
     * 充币权限（0不可以 1可以）
     */
    private Integer canDeposit;

}
