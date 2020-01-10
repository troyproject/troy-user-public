package com.troy.user.dao.mapper.user;

import com.troy.user.dao.model.user.UserLoginLogsModel;
import com.troy.user.dto.out.user.login.UserLoginLogDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserLoginLogsMapper
 *
 * @author zhangchengjie
 * @date 2019/07/29
 */
@Repository
public interface UserLoginLogsMapper {

    /**
     * 新增
     *
     * @param userLoginLogs
     * @return
     */
    Integer insert(UserLoginLogsModel userLoginLogs);

    /**
     * 查询日志
     *
     * @param top
     * @param userId
     * @return
     * @throws Exception
     */
    List<UserLoginLogDetails> selectForUserLoginLogDetails(@Param("userId") Long userId, @Param("top") Integer top) throws Exception;

}
