package com.troy.user.service.internal.user;

import com.troy.user.dao.model.user.AfterRegisterEventModel;

/**
 * 注册后事件服务
 *
 * @author dp
 */
public interface IAfterRegisterEventService {

    Integer insertAfterRegisterEvent(AfterRegisterEventModel afterRegisterEventModel);
}
