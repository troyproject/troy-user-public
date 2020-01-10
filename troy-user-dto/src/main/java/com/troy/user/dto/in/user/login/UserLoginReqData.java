package com.troy.user.dto.in.user.login;

import com.troy.commons.dto.in.ReqData;

public class UserLoginReqData extends ReqData {

    private static final long serialVersionUID = -2593922958553672863L;

    private String mobile;
    private String email;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
