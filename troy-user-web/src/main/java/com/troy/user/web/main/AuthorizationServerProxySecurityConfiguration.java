package com.troy.user.web.main;


import com.troy.user.api.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * description 配置 oauth2 proxy
 *
 * @author Han
 * @date 2018/9/30 14:30
 */
@Configuration
@Order(1)
public class AuthorizationServerProxySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private List<AuthorizationServerConfigurer> configurers = Collections.emptyList();
    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * description: 配置安全访问鉴权 HTTP 服务
     *
     * @param
     * @return
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthorizationServerSecurityConfigurer configurer = new AuthorizationServerSecurityConfigurer();
        this.configure(configurer);
        http.apply(configurer);
        String tokenEndpointPath = Constants.URL_OAUTH_PROXY_TOKEN;
        String refreshTokenEndpointPath = Constants.URL_OAUTH_PROXY_TOKEN_REFRESH;
        String tokenKeyPath = Constants.URL_OAUTH_PROXY_TOKEN_KEY;
        String checkTokenPath = Constants.URL_OAUTH_PROXY_TOKEN_CHECK;
        http
                .authorizeRequests()
                .antMatchers(tokenEndpointPath).fullyAuthenticated()
                .antMatchers(refreshTokenEndpointPath).fullyAuthenticated()
                .antMatchers(tokenKeyPath).access(configurer.getTokenKeyAccess())
                .antMatchers(checkTokenPath).access(configurer.getCheckTokenAccess())
                .and()
                .requestMatchers()
                .antMatchers(tokenEndpointPath, refreshTokenEndpointPath, tokenKeyPath, checkTokenPath)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.setSharedObject(ClientDetailsService.class, clientDetailsService);
    }

    /**
     * description: 配置鉴权服务安全
     *
     * @param
     * @return
     */
    protected void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
        Iterator<AuthorizationServerConfigurer> iterator = this.configurers.iterator();
        while (iterator.hasNext()) {
            iterator.next().configure(authorizationServerSecurityConfigurer);
        }
    }
}
