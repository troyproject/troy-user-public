package com.troy.user.service.internal.token;

import com.troy.user.dto.converter.token.CustomUserAuthenticationConverter;
import com.troy.user.service.configurator.properties.KeyStoreProperties;
import com.troy.user.service.configurator.properties.MakeTokenProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import java.security.KeyPair;

/**
 * description 定制令牌转换器
 *
 * @author Han
 * @date 2018-10-15 15:13
 */
@Component
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {


    @Autowired
    public CustomJwtAccessTokenConverter(KeyStoreProperties keyStoreProperties, MakeTokenProperties makeTokenProperties) {
        String path = keyStoreProperties.getPath();
        if(StringUtils.isBlank(path)){
            throw new IllegalArgumentException("keyStoreProperties 的 path未设置");
        }
        Resource store;
        if(path.startsWith("file://")){
            PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            propertiesFactoryBean.setIgnoreResourceNotFound(false);
            store = resolver.getResource(path);
        }else{
            store = new ClassPathResource(path);
        }

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(store, keyStoreProperties.getPassword().toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyStoreProperties.getAlias(), keyStoreProperties.getKeyPassword().toCharArray());
        this.setKeyPair(keyPair);
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        tokenConverter.setIncludeGrantType(makeTokenProperties.getRespIncludeGrantType());
        tokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());
        this.setAccessTokenConverter(tokenConverter);
    }
}
