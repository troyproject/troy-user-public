package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqPage;

/**
 * 系统应用账户查询入参
 *
 * @author:
 * @date: 2018-10-10 11:21
 * @copyright
 */
public class UserReqData extends ReqPage {

    private static final long serialVersionUID = 183687330483313850L;

    private String userId;
    /**
     * 用户名
     */
    private String username;
    private String mobile;
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
