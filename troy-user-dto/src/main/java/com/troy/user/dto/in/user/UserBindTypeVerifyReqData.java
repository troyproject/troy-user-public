package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangchengjie
 * @date 2019/07/30
 */
@Setter
@Getter
public class UserBindTypeVerifyReqData extends ReqData {

    private static final long serialVersionUID = -1L;

    /** 绑定校验类型 1邮箱 2手机 3谷歌验证码 */
    public static final Integer bindType_1 = 1;
    /** 绑定校验类型 1邮箱 2手机 3谷歌验证码 */
    public static final Integer bindType_2 = 2;
    /** 绑定校验类型 1邮箱 2手机 3谷歌验证码 */
    public static final Integer bindType_3 = 3;

    /** 操作类型 1绑定 2解绑 */
    public static final Integer OPERATETYPE_1 = 1;
    /** 操作类型 1绑定 2解绑 */
    public static final Integer OPERATETYPE_2 = 2;

    /**
     * 绑定校验类型 1邮箱 2手机 3谷歌验证码
     */
    @ApiModelProperty(value = "绑定校验类型 1邮箱 2手机 3谷歌验证码")
    private Integer bindType;

    /**
     * 操作类型 1绑定 2解绑
     */
    @ApiModelProperty(value = "操作类型 1绑定 2解绑")
    private Integer operateType;

    /**
     * 用户id
     */
    private transient Long userId;

}
