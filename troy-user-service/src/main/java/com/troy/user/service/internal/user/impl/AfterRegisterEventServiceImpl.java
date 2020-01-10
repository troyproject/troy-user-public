package com.troy.user.service.internal.user.impl;

import com.troy.user.dao.mapper.user.AfterRegisterEventMapper;
import com.troy.user.dao.model.user.AfterRegisterEventModel;
import com.troy.user.service.internal.user.IAfterRegisterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册后事件服务
 *
 * @author dp
 */
@Service
public class AfterRegisterEventServiceImpl implements IAfterRegisterEventService {

    @Autowired
    AfterRegisterEventMapper afterRegisterEventMapper;

    @Override
    public Integer insertAfterRegisterEvent(AfterRegisterEventModel afterRegisterEventModel) {
        return afterRegisterEventMapper.insert(afterRegisterEventModel);
    }
}
