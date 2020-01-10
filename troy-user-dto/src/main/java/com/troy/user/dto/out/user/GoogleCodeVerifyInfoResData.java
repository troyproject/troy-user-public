package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author zhangchengjie
 * @date 2019/08/05
 */
@Setter
@Getter
@Builder
public class GoogleCodeVerifyInfoResData extends ResData {

    @ApiModelProperty("谷歌码校验凭证")
    private String googleCodeAuthToken;

}
