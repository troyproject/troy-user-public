package com.troy.user.service.internal.user;

import com.troy.user.dto.in.user.login.UserLoginLogReqData;
import com.troy.user.dto.out.user.UserDetails;
import com.troy.user.dto.out.user.login.UserLoginLogDetails;
import com.troy.user.dto.out.user.login.UserLoginLogResData;

import java.util.List;

/**
 * description 用户登录日志
 *
 * @author Han
 * @date 2018-09-30 10:50
 */
public interface UserLoginLogService {

    /**
     * 保存登录日志
     *
     * @param ip
     * @param channel
     * @param loginUsername
     * @param userDetails
     * @throws Exception
     */
    void save(String ip, Integer channel, String loginUsername, UserDetails userDetails);

    /**
     * 近期登录日志
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<UserLoginLogDetails> listRecent(Long userId) throws Exception;

    UserLoginLogResData list(Long userId) throws Exception;

    /**
     * 登陆日志-分页
     * @param reqData
     * @return
     * @throws Exception
     */
    UserLoginLogResData list(UserLoginLogReqData reqData) throws Exception;
}
