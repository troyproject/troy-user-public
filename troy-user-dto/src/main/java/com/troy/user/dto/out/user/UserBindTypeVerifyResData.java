package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
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
public class UserBindTypeVerifyResData extends ResData {

    private static final long serialVersionUID = 1L;

    /**
     * 验证方式 1邮箱 2手机 3谷歌
     */
    public static final Integer verifyWay_1 = 1;
    /**
     * 验证方式 1邮箱 2手机 3谷歌
     */
    public static final Integer verifyWay_2 = 2;
    /**
     * 验证方式 1邮箱 2手机 3谷歌
     */
    public static final Integer verifyWay_3 = 3;

    /**
     * 验证方式 1邮箱 2手机 3谷歌
     */
    @ApiModelProperty(value = "验证方式 1邮箱 2手机 3谷歌, 类型:数组 （例如存在两种验证方式[1, 2]）")
    private List<Integer> verifyWay;

    /**
     * 谷歌验证码密钥
     */
    @ApiModelProperty(value = "谷歌验证码密钥")
    private String googleSecretKey;

    /**
     * 谷歌验证码秘钥二维码
     */
    @ApiModelProperty(value = "谷歌验证码秘钥二维码内容")
    private String googleQrCode;

    /**
     * 谷歌验证码账户名
     */
    @ApiModelProperty(value = "谷歌验证码账户名")
    private String googleUsername;

}
