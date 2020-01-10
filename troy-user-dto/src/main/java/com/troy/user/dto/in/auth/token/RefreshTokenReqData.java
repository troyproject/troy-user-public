package com.troy.user.dto.in.auth.token;


import com.troy.commons.dto.in.ReqData;

import java.util.Set;

/**
 * description 刷新令牌请求入参
 *
 * @author Han
 * @date 2018/10/12 10:01
 */
public class RefreshTokenReqData extends ReqData {

    private static final long serialVersionUID = 2996290991542373325L;

    private Set<String> scope;
    private String refreshToken;

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
