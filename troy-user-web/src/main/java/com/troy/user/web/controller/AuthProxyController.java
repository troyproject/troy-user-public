package com.troy.user.web.controller;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.commons.proxy.GreedyRequestProxy;
import com.troy.user.api.AuthProxyApi;
import com.troy.user.dto.constants.KeyConstants;
import com.troy.user.dto.converter.TypeConverter;
import com.troy.user.dto.converter.token.*;
import com.troy.user.dto.in.auth.token.AccessTokenReqData;
import com.troy.user.dto.in.auth.token.CheckTokenReqData;
import com.troy.user.dto.in.auth.token.RefreshTokenReqData;
import com.troy.user.dto.in.auth.token.TokenKeyReqData;
import com.troy.user.dto.out.auth.token.CheckTokenResData;
import com.troy.user.dto.out.auth.token.TokenKeyResData;
import com.troy.user.dto.out.auth.token.TokenResData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * description 代理鉴权 HTTP 服务
 *
 * @author Han
 * @date 2018-09-30 15:01
 */
@RestController
@Api(tags = "授权服务")
public class AuthProxyController extends AbstractController implements AuthProxyApi {

    @Autowired
    @Qualifier("customJwtAccessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;
    private TypeConverter<AccessTokenReqData, Map<String, String>> accessTokenReqDataConverter = new AccessTokenReqDataConverter();
    private TypeConverter<RefreshTokenReqData, Map<String, String>> refreshTokenReqDataConverter = new RefreshTokenReqDataConverter();
    private TypeConverter<OAuth2AccessToken, TokenResData> tokenResDataConverter = new TokenResDataConverter();
    private TypeConverter<Map<String, String>, TokenKeyResData> tokenKeyResDataConverter = new TokenKeyResDataConverter();
    private TypeConverter<Map<String, ?>, CheckTokenResData> checkTokenResDataConverter = new CheckTokenResDataConverter();

    @Override
    @ApiOperation(value = "授权", notes = "支持`authorization_code`、`client_credentials`、`implicit`、`password`等授权模式")
    public Res<TokenResData> getToken(@RequestBody Req<AccessTokenReqData> req) {
        Res<TokenResData> res = super.process(req, (GreedyRequestProxy<AccessTokenReqData, TokenResData>) (reqHead, reqData) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Map<String, String> parameterMap = this.accessTokenReqDataConverter.convert(reqData);
            parameterMap.put(KeyConstants.AUTHENTICATION_IP, super.getIpAddress());
            ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(authentication, parameterMap);
            TokenResData tokenResData = tokenResDataConverter.convert(responseEntity.getBody());
            return tokenResData;
        });
        return res;
    }

    @Override
    @ApiOperation(value = "刷新令牌", notes = "可凭`RefreshToken`换取新的令牌")
    public Res<TokenResData> refreshToken(@RequestBody Req<RefreshTokenReqData> req) {
        return super.process(req, (GreedyRequestProxy<RefreshTokenReqData, TokenResData>) (reqHead, reqData) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Map<String, String> parameterMap = this.refreshTokenReqDataConverter.convert(reqData);
            ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(authentication, parameterMap);
            TokenResData tokenResData = tokenResDataConverter.convert(responseEntity.getBody());
            return tokenResData;
        });
    }

    @Override
    @ApiOperation(value = "校验`AccessToken`", notes = "校验`AccessToken`是否真实、是否过期")
    public Res<CheckTokenResData> checkToken(@RequestBody Req<CheckTokenReqData> req) {
        return super.process(req, (GreedyRequestProxy<CheckTokenReqData, CheckTokenResData>) (reqHead, reqData) -> {
            Map<String, ?> result = this.checkTokenEndpoint.checkToken(reqData.getToken());
            CheckTokenResData checkTokenResData = this.checkTokenResDataConverter.convert(result);
            return checkTokenResData;
        });
    }

    @Override
    @ApiOperation(value = "获取公钥", notes = "返回公钥及加密算法，客户端可凭此公钥本地校验令牌")
    public Res<TokenKeyResData> getTokenKey(@RequestBody Req<TokenKeyReqData> req) {
        return super.process(req, (GreedyRequestProxy<TokenKeyReqData, TokenKeyResData>) (reqHead, reqData) -> {
            if (!this.jwtAccessTokenConverter.isPublic()) {
                return null;
            }
            Map<String, String> map = this.jwtAccessTokenConverter.getKey();
            TokenKeyResData tokenKeyResData = this.tokenKeyResDataConverter.convert(map);
            return tokenKeyResData;
        });
    }
}
