package com.troy.user.dto.in.user;

import com.troy.user.dto.in.notify.VerificationCodeReqData;
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
public class UserBindSendCodeReqData extends VerificationCodeReqData {

    private static final long serialVersionUID = -1L;

    /**
     * 绑定类型 1邮箱 2手机 3谷歌验证码
     */
    @ApiModelProperty(value = "绑定类型 1邮箱 2手机 3谷歌验证码")
    private Integer bindType;

    /**
     * 验证方式 1邮箱 2手机 3谷歌
     */
    @ApiModelProperty(value = "验证方式 1邮箱 2手机 3谷歌, 这里是数组")
    private List<Integer> verifyWays;

    /**
     * 发送方式 1邮箱 2手机 3谷歌
     */
    @ApiModelProperty(value = "发送方式 1邮箱 2手机 3谷歌")
    private Integer sendWay;

    /**
     * 操作类型 1绑定 2解绑
     */
    @ApiModelProperty(value = "操作类型 1绑定 2解绑")
    private Integer operateType;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱 绑定邮箱需传")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号 绑定手机号需传")
    private String mobile;

    /**
     * 手机号区号
     */
    @ApiModelProperty(value = "手机号区号")
    private String phoneAreaCode;

    /**
     * 用户Id
     */
    private transient Long userId;

    /**
     * 发送验证码模板类型
     */
    private String type;

//    @ApiModelProperty(value = "中英文 0英文 1中文（默认中文）")
//    private Integer language;

}
