package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhangchengjie
 * @date 2019/07/30
 */
@Setter
@Getter
public class UserBindReqData extends ReqData {

    private static final long serialVersionUID = -1L;

    /**
     * 绑定类型 1邮箱 2手机 3谷歌验证码
     */
    @ApiModelProperty(value = "绑定校验类型 1邮箱 2手机 3谷歌验证码")
    private Integer bindType;

    /**
     * 验证方式 1邮箱 2手机 3谷歌
     */
    @ApiModelProperty(value = "验证方式 1邮箱 2手机 3谷歌")
    private List<Integer> verifyWays;

    /**
     * 操作类型 1绑定 2解绑
     */
    @ApiModelProperty(value = "操作类型 1绑定 2解绑")
    private Integer operateType;

    /**
     * 邮箱验证码
     */
    @ApiModelProperty(value = "邮箱验证码")
    private String emailCode;
    /**
     * 手机验证码
     */
    @ApiModelProperty(value = "手机验证码")
    private String mobileCode;

    /**
     * 谷歌验证码
     */
    @ApiModelProperty(value = "谷歌验证码")
    private String googleCode;

    /**
     * 谷歌验证码密钥
     */
    private String googleSecretKey;

    /**
     * 用户Id
     */
    private transient Long userId;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 手机号区号
     */
    @ApiModelProperty(value = "手机号区号")
    private String phoneAreaCode;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty("谷歌码校验凭证")
    private String googleCodeAuthToken;

}
