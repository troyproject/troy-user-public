package com.troy.user.dto.out.user.add;

import com.troy.commons.dto.out.ResData;

/**
 * 添加账号请求入参
 *
 * @author:
 * @date: 2018-10-10 11:21
 * @copyright
 */
public class UserRegisterResData extends ResData {

    private static final long serialVersionUID = -3805651623338562983L;

    private Integer countRegistered;

    public Integer getCountRegistered() {
        return countRegistered;
    }

    public void setCountRegistered(Integer countRegistered) {
        this.countRegistered = countRegistered;
    }
}
