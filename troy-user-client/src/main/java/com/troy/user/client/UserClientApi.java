package com.troy.user.client;

import com.troy.user.client.auth.AuthProxyClient;
import com.troy.user.client.auth.TokenConverter;

/**
 * description 用户客户端 API
 *
 * @author Han
 * @date 2018-10-24 10:58
 */
public class UserClientApi {

    /**
     * description: 创建令牌转换器
     *
     * @param
     * @return
     */
    public static TokenConverter createTokenConverter(TokenConverter.Builder builder) {
        return builder == null ? null : builder.build();
    }

    /**
     * description: 创建鉴权客户端
     *
     * @param
     * @return
     */
    public static AuthProxyClient createAuthProxyClient(AuthProxyClient.Builder builder) {
        return builder == null ? null : builder.build();
    }
}
