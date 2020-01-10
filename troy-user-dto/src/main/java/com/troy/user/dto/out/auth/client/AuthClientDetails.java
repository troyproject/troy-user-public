package com.troy.user.dto.out.auth.client;


import com.troy.commons.dto.out.ResData;

import java.util.Date;
import java.util.Set;

/**
 * description 鉴权客户端详情
 *
 * @author Han
 * @date 2018-10-09 14:03
 */
public class AuthClientDetails extends ResData {

    private static final long serialVersionUID = 1531745643436841474L;

    private Long id;
    /**
     * createBy
     */
    private String createBy;
    /**
     * createTime
     */
    private java.util.Date createTime;
    /**
     * updateBy
     */
    private String updateBy;
    /**
     * updateTime
     */
    private java.util.Date updateTime;
    /**
     * lastUpdateFromIp
     */
    private String lastUpdateFromIp;
    private Integer    version;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastUpdateFromIp() {
        return lastUpdateFromIp;
    }

    public void setLastUpdateFromIp(String lastUpdateFromIp) {
        this.lastUpdateFromIp = lastUpdateFromIp;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
