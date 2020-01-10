package com.troy.user.dto.out.user.login;

import com.troy.commons.dto.out.ResData;

public class UserLoginBeforeResData extends ResData {

    private static final long serialVersionUID = -2593610950732572863L;

    /**
     * @see com.troy.user.dto.constants.UserBindTypeEnum
     */
    private Integer validationType;
    private String phoneAreaCode;
    private String phoneNumber;
    private String email;

    public Integer getValidationType() {
        return validationType;
    }

    public void setValidationType(Integer validationType) {
        this.validationType = validationType;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
