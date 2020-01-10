package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqPage;

/**
 * 用户数查询入参
 *
 * @author:
 * @date: 2018-10-10 11:21
 * @copyright
 */
public class UserCountReqData extends ReqPage {

    private static final long serialVersionUID = 183687330483313850L;

    /**
     * @see com.troy.user.dto.constants.Sex
     */
    private Integer sex;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
