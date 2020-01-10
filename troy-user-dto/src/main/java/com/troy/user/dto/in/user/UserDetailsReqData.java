package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;

/**
 * @author Han
 */
public class UserDetailsReqData extends ReqData {

    private static final long serialVersionUID = -2593610950732572863L;

    private String loginUsername;

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }
}
