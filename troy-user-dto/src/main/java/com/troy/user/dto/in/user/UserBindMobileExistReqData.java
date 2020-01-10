package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.in.ReqPage;

/**
 * 用户数查询是否绑定手机号
 *
 * @author:caq
 * @date: 2019-10-08 20:21
 * @copyright
 */
public class UserBindMobileExistReqData extends ReqData {


    /**
     * 手机号
     */
    private String mobile;

    /**
     * '是否绑定手机 （0未绑定 1绑定）'
     */
    private Integer bindMobile;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(Integer bindMobile) {
        this.bindMobile = bindMobile;
    }
}
