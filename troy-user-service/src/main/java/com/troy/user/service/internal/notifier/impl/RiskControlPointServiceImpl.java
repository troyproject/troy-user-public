package com.troy.user.service.internal.notifier.impl;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqFactory;
import com.troy.commons.dto.in.ReqHead;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.notifier.dto.in.sms.SmsSendReqData;
import com.troy.user.dto.in.notify.RiskControlReqData;
import com.troy.user.dto.in.notify.VerificationCodeReqData;
import com.troy.user.service.configurator.properties.AppInfoProperties;
import com.troy.user.service.foreign.notifier.SmsClient;
import com.troy.user.service.internal.notifier.RiskControlPointService;
import com.troy.user.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiskControlPointServiceImpl implements RiskControlPointService{

    @Autowired
    @Qualifier("riskControlReqDataValidator")
    private Validator<RiskControlReqData> validator;

    @Autowired
    private SmsClient smsClient;

    @Autowired
    private AppInfoProperties appInfoProperties;

    @Override
    public boolean sendForSms(RiskControlReqData riskControlReqData) {
        this.validator.verify(riskControlReqData);
        ReqHead head = new ReqHead();
        head.setClientId(this.appInfoProperties.getAppId());
        SmsSendReqData data = new SmsSendReqData();
        String content = riskControlReqData.getContent();
        String phoneAreaCode = riskControlReqData.getPhoneAreaCode();
        String phoneNumber = riskControlReqData.getPhoneNumber();
        data.setContent(content);
        data.setPhoneAreaCode(phoneAreaCode);
        data.setPhoneNumber(phoneNumber);
        data.setHuaXin(riskControlReqData.getHuaxin());
        Req<SmsSendReqData> req = ReqFactory.getInstance().createReq(head, data);
        Res<ResData> res = this.smsClient.send(req);
        if (res == null || res.getHead() == null || !StateTypeSuper.SUCCESS_DEFAULT.getCode().equals(res.getHead().getCode())) {
            return false;
        }
        return false;
    }


    public static void main(String[] args) {
    }
}
