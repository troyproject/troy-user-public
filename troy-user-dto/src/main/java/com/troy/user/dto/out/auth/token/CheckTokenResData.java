package com.troy.user.dto.out.auth.token;

import com.troy.commons.dto.out.ResData;
import com.troy.user.dto.constants.GrantType;

import java.util.Set;

/**
 * description 校验令牌请求出参
 *
 * @author Han
 * @date 2018/10/12 9:56
 */
public class CheckTokenResData extends ResData {

    private static final long serialVersionUID = 2996290991437614391L;

    /**
     * 授权类型
     *
     * @see GrantType
     */
    private String grantType;
    /**
     * 到期时间戳，精确到秒
     */
    private Integer expiration;
    private Long userId;
    private String username;
    private String clientId;
    private Set<String> scope;
    private String tokenId;
    private String accessTokenId;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
