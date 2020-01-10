package com.troy.user.client.auth;

import com.troy.user.client.constants.Constants;
import com.troy.user.dto.converter.TypeConverter;
import com.troy.user.dto.converter.token.CheckTokenResDataConverter;
import com.troy.user.dto.converter.token.CustomUserAuthenticationConverter;
import com.troy.user.dto.out.auth.token.CheckTokenResData;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * description 令牌转换器
 *
 * @author Han
 * @date 2018-10-24 11:04
 */
public class TokenConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenConverter.class);

    private JwtAccessTokenConverter jwtAccessTokenConverter;
    private TokenStore tokenStore;
    private TypeConverter<Map<String, ?>, CheckTokenResData> checkTokenResDataConverter = new CheckTokenResDataConverter();

    protected TokenConverter(String publicKey) {
        SignatureVerifier signatureVerifier = new RsaVerifier(publicKey);
        this.jwtAccessTokenConverter = new JwtAccessTokenConverter();
        this.jwtAccessTokenConverter.setVerifier(signatureVerifier);
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        tokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());
        this.jwtAccessTokenConverter.setAccessTokenConverter(tokenConverter);
        this.tokenStore = new JwtTokenStore(this.jwtAccessTokenConverter);
    }

    /**
     * description: 转换访问令牌
     *
     * @param token
     * @return
     */
    public CheckTokenResData convertAccessToken(String token) throws Exception {
        try {
            OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(token);
            OAuth2Authentication authentication = this.tokenStore.readAuthentication(token);
            Map<String, ?> response = this.jwtAccessTokenConverter.convertAccessToken(accessToken, authentication);
            CheckTokenResData checkTokenResData = checkTokenResDataConverter.convert(response);
            return checkTokenResData;
        } catch (Exception e) {
            LOGGER.error("The token[{}] is invalid", token);
            throw e;
        }
    }

    /**
     * description: 转换访问令牌
     *
     * @param request
     * @return
     */
    public CheckTokenResData convertAccessToken(HttpServletRequest request) throws Exception {
        String token = this.readAccessToken(request);
        return this.convertAccessToken(token);
    }

    /**
     * description: 校验访问令牌
     *
     * @param token
     * @return
     */
    public void checkAccessToken(String token) throws InvalidTokenException {
        OAuth2AccessToken accessToken;
        try {
            accessToken = this.tokenStore.readAccessToken(token);
        } catch (Exception e) {
            LOGGER.error("The token[{}] is invalid", token);
            throw e;
        }
        if (accessToken == null) {
            throw new InvalidTokenException("Token was not recognised.");
        }
        if (accessToken.isExpired()) {
            throw new InvalidTokenException("Token has expired.");
        }
    }

    /**
     * description: 校验访问令牌
     *
     * @param request
     * @return
     */
    public void checkAccessToken(HttpServletRequest request) throws InvalidTokenException {
        String token = this.readAccessToken(request);
        this.checkAccessToken(token);
    }

    /**
     * description: 读取访问令牌
     *
     * @param
     * @return
     */
    public String readAccessToken(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(authorization)) {
            return authorization;
        }
        String[] typeAndValue = authorization.split(" ");
        String token = typeAndValue[typeAndValue.length - 1].trim();
        if (Constants.DEFAULT_EMPTY_TOKEN.equalsIgnoreCase(token)) {
            return null;
        }
        return token;
    }

    /**
     * description: 创建构造器
     *
     * @return
     */
    public static TokenConverter.Builder builder() {
        return new TokenConverter.Builder();
    }


    /**
     * description 令牌转换器构造器
     *
     * @author Han
     * @date 2018/10/24 11:47
     */
    public static class Builder {

        private String publicKey;

        public Builder withPublicKey(String publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public TokenConverter build() {
            return new TokenConverter(this.publicKey);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
