package com.troy.user.dao.mapper.user;

import com.troy.user.dao.model.user.AfterRegisterEventModel;
import org.springframework.stereotype.Repository;

/**
 * 注册后事件
 *
 * @author dp
 */
@Repository
public interface AfterRegisterEventMapper {

    /**
     * 保存
     *
     * @param afterRegisterEventModel
     * @return
     */
    Integer insert(AfterRegisterEventModel afterRegisterEventModel);
}
