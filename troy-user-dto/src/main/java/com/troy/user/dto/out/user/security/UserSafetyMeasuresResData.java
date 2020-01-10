package com.troy.user.dto.out.user.security;

import com.troy.commons.dto.out.ResData;

import java.util.List;

public class UserSafetyMeasuresResData extends ResData {

    private static final long serialVersionUID = -2593610950732572863L;

    /**
     * @see com.troy.user.dto.constants.UserBindTypeEnum
     */
    private List<Integer> validationTypeList;
    private String phoneAreaCode;
    private String phoneNumber;
    private String email;

    public List<Integer> getValidationTypeList() {
        return validationTypeList;
    }

    public void setValidationTypeList(List<Integer> validationTypeList) {
        this.validationTypeList = validationTypeList;
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
