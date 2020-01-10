package com.troy.user.dto.in.auth.client;

import com.troy.commons.dto.in.ReqData;
import com.troy.user.dto.constants.GrantType;
import com.troy.user.dto.constants.YesOrNo;

import java.util.Set;

/**
 * description 添加鉴权客户端请求入参
 *
 * @author Han
 * @date 2018-10-18 15:25
 */
public class AuthClientAddReqData extends ReqData {

    private static final long serialVersionUID = 2996290991543256346L;

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
     * @see GrantType
     */
    private Set<String> grantTypeSet;
    /**
     * 访问令牌有效期（秒）
     */
    private Integer accessTokenValidity;
    /**
     * 刷新令牌有效期（秒）
     */
    private Integer refreshTokenValidity;
    /**
     * 说明
     */
    private String explain;

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
