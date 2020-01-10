package com.troy.user.dto.in.user.security;

import com.troy.commons.dto.in.ReqData;

/**
 * 查询用户拥有的安全措施入参
 */
public class UserSafetyMeasuresReqData extends ReqData {

    private static final long serialVersionUID = -2593922958553672863L;

    private Long userId;
    private String loginUsername;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }
}
