package com.troy.user.web.main;

import com.troy.user.service.internal.auth.AuthService;
import com.troy.user.web.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * description 鉴权配置
 *
 * @author Han
 * @date 2018/9/27 16:51
 */
@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("customTokenServices")
    private DefaultTokenServices defaultTokenServices;
    @Autowired
    @Qualifier("customJwtAccessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    @Qualifier("customJwtTokenStore")
    private JwtTokenStore jwtTokenStore;
    @Autowired
    private AuthService authService;
    @Autowired
    @Qualifier("userLoginAuthenticationManager")
    private AuthenticationManager authenticationManager;

    /**
     * 定义授权和令牌端点和令牌服务。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .pathMapping(Constants.DEFAULT_URL_OAUTH_NORMAL_TOKEN, com.troy.user.api.constants.Constants.URL_OAUTH_NORMAL_TOKEN)
                .pathMapping(Constants.DEFAULT_URL_OAUTH_NORMAL_CHECK_TOKEN, com.troy.user.api.constants.Constants.URL_OAUTH_NORMAL_TOKEN_CHECK)
                .pathMapping(Constants.DEFAULT_URL_OAUTH_NORMAL_TOKEN_KEY, com.troy.user.api.constants.Constants.URL_OAUTH_NORMAL_TOKEN_KEY)
                .tokenServices(this.defaultTokenServices)
                .tokenStore(this.jwtTokenStore)
                .tokenEnhancer(this.jwtAccessTokenConverter)
                .accessTokenConverter(this.jwtAccessTokenConverter)
                .authenticationManager(this.authenticationManager)
                //因 TokenEndpoint 内置 ExceptionHandler，而异常处理后的响应消息通过 translate() 获取
                .exceptionTranslator(new DefaultWebResponseExceptionTranslator() {
                    @Override
                    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                        //向上抛出，以统一处理异常
                        throw e;
                    }
                })
        ;
    }

    /**
     * description: 配置令牌相关服务的访问权限。
     *
     * @param
     * @return
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                //开放公钥访问接口
                .tokenKeyAccess(Constants.ACCESS_EXPRESSION_TOKEN_KEY)
                //对非匿名用户开放验证 token 接口
                .checkTokenAccess(Constants.ACCESS_EXPRESSION_CHECK_TOKEN)
        ;
    }

    /**
     * description: 配置客户端详细信息服务的配置程序。客户端的详细信息可以初始化，也可以参考现有的存储。
     *
     * @param
     * @return
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(this.authService);
    }
}
