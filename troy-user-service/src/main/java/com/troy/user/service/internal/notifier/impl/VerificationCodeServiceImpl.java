package com.troy.user.service.internal.notifier.impl;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqFactory;
import com.troy.commons.dto.in.ReqHead;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.notifier.dto.constants.EmailType;
import com.troy.notifier.dto.in.email.EmailSendReqData;
import com.troy.notifier.dto.in.sms.SmsSendReqData;
import com.troy.user.dto.constants.*;
import com.troy.user.dto.in.notify.RiskJudgeCodeReqData;
import com.troy.user.dto.in.notify.VerificationCodeReqData;
import com.troy.user.service.configurator.properties.AppInfoProperties;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.constants.KeyConstants;
import com.troy.user.service.foreign.notifier.EmailClient;
import com.troy.user.service.foreign.notifier.SmsClient;
import com.troy.user.service.internal.notifier.VerificationCodeService;
import com.troy.user.service.validator.Validator;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author Han
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);

    @Autowired
    @Qualifier("verificationCodeReqDataValidator")
    private Validator<VerificationCodeReqData> verificationCodeReqDataValidator;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SmsClient smsClient;
    @Autowired
    private EmailClient emailClient;
    @Autowired
    private AppInfoProperties appInfoProperties;

    @Override
    public boolean sendVerificationCode(VerificationCodeReqData reqData) {
        this.verificationCodeReqDataValidator.verify(reqData);
        TextMessageType type = TextMessageType.find(reqData.getType());
        TextMessageTemplate textMessageTemplate = TextMessageTemplate.find(LanguageType.find(reqData.getLanguage()), type);
        if (textMessageTemplate == null) {
            String errorMsg = MessageFormat.format("No message template was found,language={0},messageType={1}", reqData.getLanguage(), reqData.getType());
            throw new ServiceException(StateTypeSuper.FAIL_UNEXPECTED_RESULTS, errorMsg);
        }
        NotifyChannel channel = NotifyChannel.find(reqData.getChannel());
        String target = NotifyChannel.SMS == channel ? reqData.getPhoneNumber() : reqData.getEmail();
        String key = this.generateKey(type, target);
        String code = RandomStringUtils.random(Constants.DEFAULT_VERIFICATION_CODE_SIZE, false, true);
        this.redisTemplate.opsForValue().set(key, code, Constants.DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        LOGGER.info("Set validation code to redis successfully,key={},value={}", key, code);
        if (NotifyChannel.SMS == channel) {
            String content = MessageFormat.format(textMessageTemplate.getSmsTemplate(), code, Constants.DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES);
            return this.sendForSms(reqData.getPhoneAreaCode(), reqData.getPhoneNumber(), content);
        } else if (NotifyChannel.EMAIL == channel) {
            String content = MessageFormat.format(textMessageTemplate.getEmailTemplate(), code, Constants.DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES);
            return this.sendForEmail(reqData.getEmail(), EmailType.HTML, textMessageTemplate.getEmailSubject(), content);
        }
        return false;
    }

    @Override
    public boolean sendRiskControlCode(VerificationCodeReqData reqData) {
        this.verificationCodeReqDataValidator.verify(reqData);
        TextMessageType type = TextMessageType.find(reqData.getType());
        TextMessageTemplate textMessageTemplate = TextMessageTemplate.find(LanguageType.find(reqData.getLanguage()), type);
        if (textMessageTemplate == null) {
            String errorMsg = MessageFormat.format("No message template was found,language={0},messageType={1}", reqData.getLanguage(), reqData.getType());
            throw new ServiceException(StateTypeSuper.FAIL_UNEXPECTED_RESULTS, errorMsg);
        }
        NotifyChannel channel = NotifyChannel.find(reqData.getChannel());
        String target = NotifyChannel.SMS == channel ? reqData.getPhoneNumber() : reqData.getEmail();
        String key = this.generateRiskControlKey(type, target);
        String code = RandomStringUtils.random(Constants.DEFAULT_VERIFICATION_CODE_SIZE, false, true);
        this.redisTemplate.opsForValue().set(key, code, Constants.DEFAULT_RISK_CONTROL_CODE_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        LOGGER.info("Set risk_control code to redis successfully,key={},value={}", key, code);
        if (NotifyChannel.SMS == channel) {
            String content = MessageFormat.format(textMessageTemplate.getSmsTemplate(), code, Constants.DEFAULT_RISK_CONTROL_CODE_EXPIRATION_MINUTES);
            return this.sendForSms(reqData.getPhoneAreaCode(), reqData.getPhoneNumber(), content);
        }
        return false;
    }

    @Override
    public boolean judgePhoneCode(RiskJudgeCodeReqData reqData) {
        String phone = reqData.getPhone();
        String code = reqData.getCode();
        String key = this.generateRiskControlKey(TextMessageType.RISK_CONTROL, phone);
        String str = redisTemplate.opsForValue().get(key);
        boolean flag=false;
        if(StringUtils.isNotEmpty(str)){
            if(code.equals(str)){
/*                LOGGER.info("judge risk_control code from redis successfully,key={},value={}", key, code);*/
                flag=true;
            }
        }
        if(!flag){
            throw new VerificationException(ServiceResState.RISK_CONTROL_CODE_NOT_PASS, "mobile");
        }

        return flag;
    }

    @Override
    public boolean checkVerificationCode(TextMessageType type, String target, String currentCode) {
        if (currentCode == null || !Pattern.matches(Constants.REGEX_VERIFICATION_CODE, currentCode)) {
            return false;
        }
        String key = this.generateKey(type, target);
        /**
         * 获取并清除验证码
         */
        String code = this.redisTemplate.opsForValue().getAndSet(key, "");
        LOGGER.info("Get validation code from redis successfully,key={},value={}", key, code);
        return currentCode.equals(code);
    }

    private String generateKey(TextMessageType type, String target) {
        String key = KeyConstants.USER_VERIFICATION_CODE_PREFIX + type.getCode() + target;
        return key;
    }

    private String generateRiskControlKey(TextMessageType type, String target) {
        String key = KeyConstants.RISK_CONTROL_CODE + type.getCode() + target;
        return key;
    }


    @Override
    public boolean sendForSms(String phoneAreaCode, String phone, String content) {
        ReqHead head = new ReqHead();
        head.setClientId(this.appInfoProperties.getAppId());
        SmsSendReqData data = new SmsSendReqData();
        data.setContent(content);
        data.setPhoneAreaCode(phoneAreaCode);
        data.setPhoneNumber(phone);
        Req<SmsSendReqData> req = ReqFactory.getInstance().createReq(head, data);
        Res<ResData> res = this.smsClient.send(req);
        if (res == null || res.getHead() == null || !StateTypeSuper.SUCCESS_DEFAULT.getCode().equals(res.getHead().getCode())) {
            return false;
        }
        return true;
    }

    private boolean sendForEmail(String email, EmailType type, String subject, String content) {
        ReqHead head = new ReqHead();
        head.setClientId(this.appInfoProperties.getAppId());
        EmailSendReqData data = new EmailSendReqData();
        data.setContent(content);
        data.setSubject(subject);
        data.setEmail(email);
        data.setType(type.getCode());
        Req<EmailSendReqData> req = ReqFactory.getInstance().createReq(head, data);
        Res<ResData> res = this.emailClient.send(req);
        if (res == null || res.getHead() == null || !StateTypeSuper.SUCCESS_DEFAULT.getCode().equals(res.getHead().getCode())) {
            return false;
        }
        return true;
    }
}
