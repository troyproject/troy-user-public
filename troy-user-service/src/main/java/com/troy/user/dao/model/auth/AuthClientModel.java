package com.troy.user.dao.model.auth;

import com.troy.user.dao.model.BaseModel;

import java.util.Set;

/**
 * description 鉴权客户端数据模型
 *
 * @author Han
 * @date 2018-09-29 10:41
 */
public class AuthClientModel extends BaseModel {

    private Long id;

    /**
     * 是否启用
     *
     * @see YesOrNo
     */
    private Integer enable;
    /**
     * 客户端标识
     */
    private String clientId;
    /**
     * 客户端秘钥
     */
    private String secret;
    /**
     * 授权类型
     *
     * @see com.troy.user.dto.constants.GrantType
     */
    private Set<String> grantTypeSet;
    /**
     * 访问令牌有效期，单位：秒
     */
    private Integer accessTokenValidity;
    /**
     * 访问令牌有效期，单位：秒
     */
    private Integer refreshTokenValidity;
    /**
     * 说明
     */
    private String explain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Set<String> getGrantTypeSet() {
        return grantTypeSet;
    }

    public void setGrantTypeSet(Set<String> grantTypeSet) {
        this.grantTypeSet = grantTypeSet;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
