package com.troy.user.dto.in.user.login;

import com.troy.commons.dto.in.ReqData;

public class UserLoginBeforeReqData extends ReqData {

    private static final long serialVersionUID = -2593922950732572863L;

    private String loginUsername;
    private String password;

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
