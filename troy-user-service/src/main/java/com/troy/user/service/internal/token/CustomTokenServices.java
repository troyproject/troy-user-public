package com.troy.user.service.internal.token;

import com.troy.user.service.configurator.properties.MakeTokenProperties;
import com.troy.user.service.internal.auth.AuthService;
import com.troy.user.service.internal.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

/**
 * description 定制令牌业务实现
 *
 * @author Han
 * @date 2018-10-18 13:51
 */
@Component
public class CustomTokenServices extends DefaultTokenServices {

    @Autowired
    public CustomTokenServices(MakeTokenProperties makeTokenProperties, @Qualifier("customJwtAccessTokenConverter") JwtAccessTokenConverter jwtTokenEnhancer, @Qualifier("customJwtTokenStore")
            JwtTokenStore jwtTokenStore, AuthService authService, UserService userService) {
        this.setTokenStore(jwtTokenStore);
        if (makeTokenProperties.getSupportRefresh() != null) {
            this.setSupportRefreshToken(Boolean.valueOf(makeTokenProperties.getSupportRefresh().toString()));
        }
        if (makeTokenProperties.getReuseRefresh() != null) {
            this.setReuseRefreshToken(Boolean.valueOf(makeTokenProperties.getReuseRefresh().toString()));
        }
        this.setClientDetailsService(authService);
        this.setTokenEnhancer(jwtTokenEnhancer);
        this.setAuthenticationManager(new RefreshTokenAuthenticationManager(userService));
    }
}
