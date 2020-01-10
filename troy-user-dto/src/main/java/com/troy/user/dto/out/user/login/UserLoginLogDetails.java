package com.troy.user.dto.out.user.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Han
 */
@Setter
@Getter
public class UserLoginLogDetails implements Serializable {

    private static final long serialVersionUID = -2593632150732572863L;

    private String loginUsername;
    private String loginIp;
    private Date loginTime;
    /**
     * @see com.troy.user.dto.constants.UserLoginChannel
     */
    @ApiModelProperty(value = "登录通道，1：web；2：android；3：ios")
    private Integer channel;
}
