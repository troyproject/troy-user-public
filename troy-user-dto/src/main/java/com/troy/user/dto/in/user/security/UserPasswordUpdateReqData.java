package com.troy.user.dto.in.user.security;

import com.troy.commons.dto.in.ReqData;

public class UserPasswordUpdateReqData extends ReqData {

    private static final long serialVersionUID = -2593922958553672863L;

    private Long userId;
    private String loginUsername;
    private String password;
    private String googleVerificationCode;
    private String smsVerificationCode;
    private String emailVerificationCode;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGoogleVerificationCode() {
        return googleVerificationCode;
    }

    public void setGoogleVerificationCode(String googleVerificationCode) {
        this.googleVerificationCode = googleVerificationCode;
    }

    public String getSmsVerificationCode() {
        return smsVerificationCode;
    }

    public void setSmsVerificationCode(String smsVerificationCode) {
        this.smsVerificationCode = smsVerificationCode;
    }

    public String getEmailVerificationCode() {
        return emailVerificationCode;
    }

    public void setEmailVerificationCode(String emailVerificationCode) {
        this.emailVerificationCode = emailVerificationCode;
    }
}
