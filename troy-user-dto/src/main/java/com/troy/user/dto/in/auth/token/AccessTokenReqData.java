package com.troy.user.dto.in.auth.token;

import com.troy.commons.dto.in.ReqData;
import com.troy.user.dto.constants.GrantType;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * description 访问令牌请求入参
 *
 * @author Han
 * @date 2018/10/12 10:01
 */
public class AccessTokenReqData extends ReqData {

    private static final long serialVersionUID = 2996290991542373046L;

    /**
     * 授权类型
     *
     * @see GrantType
     */
    private String grantType;
    private Set<String> scope;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    private String verificationCode;
    /**
     * @see com.troy.user.dto.constants.UserLoginChannel
     */
    @ApiModelProperty(value = "登录通道，1：web；2：android；3：ios")
    private Integer channel;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }
}
