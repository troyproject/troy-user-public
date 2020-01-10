package com.troy.user.dto.out.auth.token;


import com.troy.commons.dto.out.ResData;

import java.util.Set;

/**
 * description 令牌请求出参
 *
 * @author Han
 * @date 2018/10/12 9:56
 */
public class TokenResData extends ResData {

    private static final long serialVersionUID = 2996290991542374391L;

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    /**
     * 距离到期秒数
     */
    private Integer expiresIn;
    private Set<String> scope;
    private String tokenId;
    private String accessTokenId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAccessTokenId() {
        return accessTokenId;
    }

    public void setAccessTokenId(String accessTokenId) {
        this.accessTokenId = accessTokenId;
    }
}
