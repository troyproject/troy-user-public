package com.troy.user.service.internal.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.commons.utils.IdWorker;
import com.troy.user.dao.mapper.user.UserLoginLogsMapper;
import com.troy.user.dao.model.user.UserLoginLogsModel;
import com.troy.user.dto.constants.UserLoginChannel;
import com.troy.user.dto.in.user.login.UserLoginLogReqData;
import com.troy.user.dto.out.user.UserDetails;
import com.troy.user.dto.out.user.login.UserLoginLogDetails;
import com.troy.user.dto.out.user.login.UserLoginLogResData;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.internal.user.UserLoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Han
 */
@Service
public class UserLoginLogServiceImpl implements UserLoginLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginLogServiceImpl.class);

    @Autowired
    private UserLoginLogsMapper userLoginLogsMapper;

    @Override
    @Async("loggerExecutor")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void save(String ip, Integer channel, String loginUsername, UserDetails userDetails) {
        UserLoginLogsModel model = new UserLoginLogsModel();
        model.setUserLoginLogsId(IdWorker.getId());
        model.setLoginIp(ip);
        model.setLoginUsername(loginUsername);
        model.setCreateTime(new Date());
        UserLoginChannel userLoginChannel = UserLoginChannel.find(channel);
        if (userLoginChannel == null) {
            LOGGER.error("Failed to save user login log,Channel[{}] is invalid", channel);
        } else {
            model.setChannel(userLoginChannel.getCode());
        }
        if (userDetails != null) {
            model.setUserId(userDetails.getUserId());
        }
        this.userLoginLogsMapper.insert(model);
    }

    @Override
    public List<UserLoginLogDetails> listRecent(Long userId) throws Exception {
        if (userId == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        List<UserLoginLogDetails> list = this.userLoginLogsMapper.selectForUserLoginLogDetails(userId, Constants.LOGIN_LOG_VIEW_MAX_SIZE);

        return list;
    }
    @Override
    public UserLoginLogResData list(Long userId) throws Exception {
        if (userId == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        List<UserLoginLogDetails> list = this.userLoginLogsMapper.selectForUserLoginLogDetails(userId,null);
        PageInfo<UserLoginLogResData> detailsPageInfo = new PageInfo(list);

        UserLoginLogResData pageInfoResDto = new UserLoginLogResData();
        if (detailsPageInfo.getSize() > 0) {
            pageInfoResDto.setList(list);
            pageInfoResDto.setTotal(detailsPageInfo.getTotal());
        }
        return pageInfoResDto;
    }

    @Override
    public UserLoginLogResData list(UserLoginLogReqData reqData) throws Exception {
        if (reqData.getUserId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        PageHelper.startPage(reqData.getPageNum(), reqData.getPageSize());
        List<UserLoginLogDetails> list = this.userLoginLogsMapper.selectForUserLoginLogDetails(reqData.getUserId(),null);
        PageInfo<UserLoginLogResData> detailsPageInfo = new PageInfo(list);

        UserLoginLogResData pageInfoResDto = new UserLoginLogResData();
        if (detailsPageInfo.getSize() > 0) {
            pageInfoResDto.setList(list);
            pageInfoResDto.setTotal(detailsPageInfo.getTotal());
        }
        return pageInfoResDto;
    }
}
