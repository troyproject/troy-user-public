package com.troy.user.dto.in.notify;

import com.troy.commons.dto.in.ReqData;

public class RiskJudgeCodeReqData extends ReqData {

    private String phone;
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
