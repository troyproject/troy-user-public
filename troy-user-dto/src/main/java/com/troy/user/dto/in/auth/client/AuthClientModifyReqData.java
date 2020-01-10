package com.troy.user.dto.in.auth.client;

import com.troy.user.dto.constants.GrantType;
import com.troy.user.dto.constants.YesOrNo;
import com.troy.user.dto.in.AbstractModifyReqData;

import java.util.Set;

/**
 * description 修改鉴权客户端请求入参
 *
 * @author Han
 * @date 2018-10-18 15:25
 */
public class AuthClientModifyReqData extends AbstractModifyReqData {

    private static final long serialVersionUID = 2996325431543256346L;

    /**
     * 是否启用
     * @see YesOrNo
     */
    private Integer enable;
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
        return this.enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getSecret() {
        return this.secret;
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
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
