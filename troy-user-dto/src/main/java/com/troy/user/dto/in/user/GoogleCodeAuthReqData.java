package com.troy.user.dto.in.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.troy.commons.dto.in.ReqData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangchengjie
 * @date 2019/08/02
 */
@Setter
@Getter
public class GoogleCodeAuthReqData extends ReqData {
    /**
     * 谷歌验证码
     */
    @ApiModelProperty(value = "谷歌验证码")
    private String googleCode;

    /**
     * 谷歌验证码密钥
     */
    @ApiModelProperty(value = "谷歌秘钥")
    private String googleSecretKey;

    @JsonIgnore
    private Long userId;
}
