package com.troy.user.service.internal.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

/**
 * description 定制令牌库
 *
 * @author Han
 * @date 2018-10-15 17:33
 */
@Component
public class CustomJwtTokenStore extends JwtTokenStore {

    @Autowired
    public CustomJwtTokenStore(@Qualifier("customJwtAccessTokenConverter") JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }
}
