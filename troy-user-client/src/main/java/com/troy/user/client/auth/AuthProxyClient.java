package com.troy.user.client.auth;

import com.alibaba.fastjson.TypeReference;
import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.commons.exception.configuration.ConfigurationException;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.user.client.constants.Constants;
import com.troy.user.client.httpclient.HttpClientApi;
import com.troy.user.client.util.TokenCache;
import com.troy.user.dto.constants.GrantType;
import com.troy.user.dto.in.auth.token.AccessTokenReqData;
import com.troy.user.dto.in.auth.token.CheckTokenReqData;
import com.troy.user.dto.in.auth.token.RefreshTokenReqData;
import com.troy.user.dto.in.auth.token.TokenKeyReqData;
import com.troy.user.dto.out.auth.token.CheckTokenResData;
import com.troy.user.dto.out.auth.token.TokenKeyResData;
import com.troy.user.dto.out.auth.token.TokenResData;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * description: 代理鉴权客户端
 *
 * @author Han
 * @date 2019-01-25 11:01
 */
public class AuthProxyClient extends AbstractAuthClient {

    private static final TokenCache TOKEN_CACHE = new TokenCache();

    private String secret;
    private String getTokenUrl;
    private String refreshTokenUrl;
    private String getTokenKeyUrl;
    private String checkTokenUrl;
    private Header authorizationHeader;

    protected AuthProxyClient(String appId, String secret, String userServerHost, int userServerPort) {
        super(appId);
        if (StringUtils.isEmpty(userServerHost)) {
            throw new ConfigurationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "userServerHost", userServerHost);
        }
        this.secret = secret;
        this.getTokenUrl = String.format(Constants.PATTERN_URL, userServerHost, userServerPort, com.troy.user.api.constants.Constants.URL_OAUTH_PROXY_TOKEN);
        this.refreshTokenUrl = String.format(Constants.PATTERN_URL, userServerHost, userServerPort, com.troy.user.api.constants.Constants.URL_OAUTH_PROXY_TOKEN_REFRESH);
        this.getTokenKeyUrl = String.format(Constants.PATTERN_URL, userServerHost, userServerPort, com.troy.user.api.constants.Constants.URL_OAUTH_PROXY_TOKEN_KEY);
        this.checkTokenUrl = String.format(Constants.PATTERN_URL, userServerHost, userServerPort, com.troy.user.api.constants.Constants.URL_OAUTH_PROXY_TOKEN_CHECK);
        String certificate = Base64.getEncoder().encodeToString(String.format(Constants.PATTERN_BASIC_AUTH_SECRET, this.appId, this.secret).getBytes());
        this.authorizationHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, String.format(com.troy.user.dto.constants.Constants.PATTERN_BASIC_AUTH, certificate));
    }

    public String getTokenValueForClient(boolean isReused, String... scopes) {
        AccessTokenReqData accessTokenReqData = new AccessTokenReqData();
        accessTokenReqData.setGrantType(GrantType.CLIENT_CREDENTIALS.getCode());
        Set<String> scopeSet = new HashSet<>(Arrays.asList(scopes));
        accessTokenReqData.setScope(scopeSet);
        Res<TokenResData> res = isReused ? this.getTokenReused(accessTokenReqData) : this.getToken(accessTokenReqData);
        if (res == null || res.getData() == null) {
            return null;
        }
        return res.getData().getAccessToken();
    }

    public Res<TokenResData> getToken(AccessTokenReqData reqData) {
        if (reqData != null) {
            if (reqData.getScope() == null || reqData.getScope().isEmpty()) {
                Set<String> defaultScope = new HashSet();
                defaultScope.add(Constants.DEFAULT_SCOPE);
                reqData.setScope(defaultScope);
            }
        }
        Req<AccessTokenReqData> req = super.createReq(reqData);
        Res<TokenResData> res = HttpClientApi.getApacheHttpClientProxy().doPost(this.getTokenUrl, req, new TypeReference<Res<TokenResData>>() {
        }, new Header[]{this.authorizationHeader});
        return res;
    }

    /**
     * 获取 Token，只有当缓存 Token 即将失效或缓存占用空间过大时才会去获取一个新的 Token
     *
     * @param reqData
     * @return
     */
    public Res<TokenResData> getTokenReused(AccessTokenReqData reqData) {
        if (reqData == null) {
            return this.getToken(reqData);
        }
        String key = this.createCacheKey(reqData.getGrantType(), reqData.getUsername(), reqData.getScope());
        Res<TokenResData> res = TOKEN_CACHE.get(key);
        if (res != null) {
            return res;
        }
        res = this.getToken(reqData);
        TOKEN_CACHE.put(key, res);
        return res;
    }

    public Res<TokenResData> refreshToken(RefreshTokenReqData reqData) {
        Req<RefreshTokenReqData> req = super.createReq(reqData);
        Res<TokenResData> res = HttpClientApi.getApacheHttpClientProxy().doPost(this.refreshTokenUrl, req, new TypeReference<Res<TokenResData>>() {
        }, new Header[]{this.authorizationHeader});
        if (res == null || res.getHead() == null || res.getData() == null || !StateTypeSuper.SUCCESS_DEFAULT.getCode().equals(res.getHead().getCode())) {
            return res;
        }
        CheckTokenReqData checkTokenReqData = new CheckTokenReqData();
        checkTokenReqData.setToken(res.getData().getAccessToken());
        Res<CheckTokenResData> checkTokenResDataResOut = this.checkToken(checkTokenReqData);
        if (checkTokenResDataResOut != null && checkTokenResDataResOut.getHead() != null && StateTypeSuper.SUCCESS_DEFAULT.getCode().equals(checkTokenResDataResOut.getHead().getCode())) {
            String key = this.createCacheKey(checkTokenResDataResOut.getData().getGrantType(), checkTokenResDataResOut.getData().getUsername(), checkTokenResDataResOut.getData().getScope());
            TOKEN_CACHE.put(key, res);
        }
        return res;
    }

    public Res<TokenKeyResData> getTokenKey(TokenKeyReqData reqIn) {
        Req<TokenKeyReqData> req = super.createReq(reqIn);
        return HttpClientApi.getApacheHttpClientProxy().doPost(this.getTokenKeyUrl, req, new TypeReference<Res<TokenKeyResData>>() {
        });
    }

    /**
     * 令牌的校验应该使用 TokenConverter 在本地完成，因此该方法不对外开放。
     *
     * @param reqData
     * @return
     */
    private Res<CheckTokenResData> checkToken(CheckTokenReqData reqData) {
        Req<CheckTokenReqData> req = super.createReq(reqData);
        return HttpClientApi.getApacheHttpClientProxy().doPost(this.checkTokenUrl, req, new TypeReference<Res<CheckTokenResData>>() {
        }, new Header[]{this.authorizationHeader});
    }

    private String createCacheKey(String grantType, String username, Set<String> scopeSet) {
        StringBuffer keyBuffer = new StringBuffer(grantType).append(username);
        if (scopeSet != null && !scopeSet.isEmpty()) {
            for (String scope : scopeSet) {
                keyBuffer.append(scope);
            }
        }
        return keyBuffer.toString();
    }

    public static AuthProxyClient.Builder builder() {
        return new AuthProxyClient.Builder();
    }

    public static class Builder {

        private String appId;
        private String secret;
        private String userServerHost;
        private int userServerPort = Constants.DEFAULT_SCHEDULER_SERVER_PORT;

        Builder() {

        }

        public AuthProxyClient.Builder withAppId(String appId) {
            this.appId = appId;
            return this;
        }

        public AuthProxyClient.Builder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public AuthProxyClient.Builder withUserServerHost(String userServerHost) {
            this.userServerHost = userServerHost;
            return this;
        }

        public AuthProxyClient.Builder withUserServerPort(Integer userServerPort) {
            if (userServerPort == null) {
                return this;
            }
            this.userServerPort = userServerPort;
            return this;
        }

        public AuthProxyClient build() {
            return new AuthProxyClient(this.appId, this.secret, this.userServerHost, this.userServerPort);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
