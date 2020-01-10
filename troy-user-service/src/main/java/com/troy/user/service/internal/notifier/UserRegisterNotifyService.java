package com.troy.user.service.internal.notifier;

import com.troy.user.dto.in.user.add.UserRegisterReqData;

public interface UserRegisterNotifyService {

    void registerNotify(UserRegisterReqData reqData);

    /**
     * 注册后事件
     *
     * @param reqData
     */
    void afterRegister(UserRegisterReqData reqData);
}
