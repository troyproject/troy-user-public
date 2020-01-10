package com.troy.user.service.internal.notifier.impl;

import com.troy.commons.exception.service.ServiceException;
import com.troy.user.dao.constants.Constants;
import com.troy.user.dao.model.user.AfterRegisterEventModel;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
import com.troy.user.dto.out.user.reward.RewardRes;
import com.troy.user.service.configurator.properties.RewardProperties;
import com.troy.user.service.foreign.notifier.RewardClient;
import com.troy.user.service.internal.notifier.UserRegisterNotifyService;
import com.troy.user.service.internal.user.IAfterRegisterEventService;
import com.troy.user.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Service
public class UserRegisterNotifyServiceImpl implements UserRegisterNotifyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterNotifyServiceImpl.class);

    @Autowired
    private RewardProperties rewardProperties;

    @Autowired
    private RewardClient rewardClient;

    @Autowired
    private IAfterRegisterEventService afterRegisterEventService;

    @Override
    @Async("loggerExecutor")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void registerNotify(UserRegisterReqData reqData) {
        if (reqData == null || StringUtils.isEmpty(reqData.getEmail())) {
            return;
        }
        if (StringUtils.isEmpty(reqData.getSource()) || !this.rewardProperties.getSources().contains(reqData.getSource())) {
            return;
        }
        // 修改数据库中的发送状态
        AfterRegisterEventModel afterRegisterEventModel = new AfterRegisterEventModel();
        afterRegisterEventModel.setAfterRegisterEventId(IdWorker.getId());
        afterRegisterEventModel.setDestination(Constants.EventDestination.HEROKUAPP.code());
        afterRegisterEventModel.setEventCode(Constants.EventCode.PUSH.code());
        afterRegisterEventModel.setEventName(Constants.EventCode.PUSH.name());
        afterRegisterEventModel.setUserId(reqData.getUserId());
        afterRegisterEventModel.setRemark(StringUtils.isNotBlank(reqData.getEmail()) ? reqData.getEmail() : reqData.getPhoneNumber());

        RewardRes res = null;
        try {
            res = this.rewardClient.send(this.rewardProperties.getToken(), this.rewardProperties.getGroupId(), this.rewardProperties.getAchievement(), reqData.getEmail());
            if (res == null || res.getOk() == null || !res.getOk()) {
                LOGGER.warn("注册后通知奖励系统失败", res);
                afterRegisterEventModel.setStatus(Constants.EventStatus.FAIL.code());
                afterRegisterEventModel.setResponse(res != null ? res.toString() : "返回数据为空");
                afterRegisterEventService.insertAfterRegisterEvent(afterRegisterEventModel);
                return;
            }
            afterRegisterEventModel.setResponse(res.toString());
            afterRegisterEventModel.setStatus(Constants.EventStatus.SUCCESS.code());
            afterRegisterEventService.insertAfterRegisterEvent(afterRegisterEventModel);
        } catch (ServiceException e) {
            String errorMsg = MessageFormat.format("Failed to send registration message,res={0},email={1},properties={2}", res, reqData.getEmail(), this.rewardProperties);
            LOGGER.error(errorMsg, e);
        }
    }

    @Override
    @Async("loggerExecutor")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void afterRegister(UserRegisterReqData reqData) {
        if (reqData == null) {
            return;
        }
        AfterRegisterEventModel afterRegisterEventModel = new AfterRegisterEventModel();
        afterRegisterEventModel.setAfterRegisterEventId(IdWorker.getId());
        afterRegisterEventModel.setDestination(Constants.EventDestination.COMMON_INVITE.code());
        afterRegisterEventModel.setEventCode(Constants.EventCode.SAVE.code());
        afterRegisterEventModel.setEventName(Constants.EventCode.SAVE.name());
        afterRegisterEventModel.setUserId(reqData.getUserId());
        afterRegisterEventModel.setRemark(StringUtils.isNotBlank(reqData.getEmail()) ? reqData.getEmail() : reqData.getPhoneNumber());
        afterRegisterEventModel.setStatus(Constants.EventStatus.SUCCESS.code());
        afterRegisterEventModel.setResponse(reqData.getInviteCode() + ":" + reqData.getRefId());

        LOGGER.info("通用注册码，注册后事件 {}", afterRegisterEventModel);
        afterRegisterEventService.insertAfterRegisterEvent(afterRegisterEventModel);
    }
}
