package com.troy.user.api;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.user.api.constants.Constants;
import com.troy.user.dto.in.auth.token.AccessTokenReqData;
import com.troy.user.dto.in.auth.token.CheckTokenReqData;
import com.troy.user.dto.in.auth.token.RefreshTokenReqData;
import com.troy.user.dto.in.auth.token.TokenKeyReqData;
import com.troy.user.dto.out.auth.token.CheckTokenResData;
import com.troy.user.dto.out.auth.token.TokenKeyResData;
import com.troy.user.dto.out.auth.token.TokenResData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * description 代理鉴权服务
 *
 * @author Han
 * @date 2018-09-30 14:42
 */
public interface AuthProxyApi {

    /**
     * description: 获取 Token
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_OAUTH_PROXY_TOKEN, method = {RequestMethod.POST}, headers = {"Authorization"})
    Res<TokenResData> getToken(Req<AccessTokenReqData> req);

    /**
     * description: 刷新 Token
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_OAUTH_PROXY_TOKEN_REFRESH, method = {RequestMethod.POST}, headers = {"Authorization"})
    Res<TokenResData> refreshToken(Req<RefreshTokenReqData> req);

    /**
     * description: 校验 Token
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_OAUTH_PROXY_TOKEN_CHECK, method = {RequestMethod.POST}, headers = {"Authorization"})
    Res<CheckTokenResData> checkToken(Req<CheckTokenReqData> req);

    /**
     * description: 获取 Token 公钥
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_OAUTH_PROXY_TOKEN_KEY, method = {RequestMethod.POST})
    Res<TokenKeyResData> getTokenKey(Req<TokenKeyReqData> req);
}
