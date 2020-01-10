package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import lombok.*;

/**
 * @author zhangchengjie
 * @date 2019/08/05
 */
@Setter
@Getter
@Builder
public class GoogleAuthenticatorResData extends ResData {

    /**
     * 谷歌验证码密钥
     */
    private String googleSecretKey;

    /**
     * 谷歌验证码秘钥二维码
     */
    private String googleQrCode;

    /**
     * 谷歌验证码账户名
     */
    private String googleUsername;

}
