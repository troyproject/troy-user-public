package com.troy.user.web.config;

import com.troy.user.client.auth.TokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * token转换配置
 *
 * @author
 * @date 2018-10-25 下午3:59:05
 * @copyright
 */
@Configuration
public class TokenConverterBeanConfig {

    @Autowired
    @Qualifier("customJwtAccessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * TODO 添加方法说明
     *
     * @return
     */
    @Bean
    TokenConverter buildTokenConverter() {
        TokenConverter.Builder builder = TokenConverter.builder();
        if (this.jwtAccessTokenConverter.isPublic()) {
            Map<String, String> map = this.jwtAccessTokenConverter.getKey();
            String publicKey = map.get(com.troy.user.dto.constants.KeyConstants.RESPONSE_TOKEN_KEY_VALUE);
            builder.withPublicKey(publicKey);
        }
        return builder.build();
    }
}
