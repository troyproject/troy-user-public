package com.troy.user.service.validator.notify;

import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dto.constants.LanguageType;
import com.troy.user.dto.constants.NotifyChannel;
import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.in.notify.VerificationCodeReqData;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * description: todo
 *
 * @author Han
 * @date 2019-01-16 16:31
 */
@Component
public class VerificationCodeReqDataValidator implements Validator<VerificationCodeReqData> {

    @Override
    public void verify(VerificationCodeReqData in) throws VerificationException {
        if (in == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        LanguageType languageType = LanguageType.find(in.getLanguage());
        if (languageType == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "language", in.getLanguage());
        }
        TextMessageType type = TextMessageType.find(in.getType());
        if (type == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "type", in.getType());
        }
        NotifyChannel channel = NotifyChannel.find(in.getChannel());
        if (channel == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "channel", in.getChannel());
        }
        if (NotifyChannel.SMS == channel) {
            if (StringUtils.isEmpty(in.getPhoneAreaCode()) || StringUtils.isEmpty(in.getPhoneNumber())) {
                throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "phoneAreaCode or phoneNumber");
            }
            if (Pattern.matches(Constants.REGEX_PHONE_AREA_CODE_DOMESTIC, in.getPhoneAreaCode()) && !Pattern.matches(Constants.REGEX_PHONE_DOMESTIC, in.getPhoneNumber())) {
                //国内手机校验
                throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "phoneNumber", in.getPhoneNumber());
            }
            //其它国家地区暂不校验
        } else if (NotifyChannel.EMAIL == channel && !Pattern.matches(Constants.REGEX_EMAIL, in.getEmail())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "email", in.getEmail());
        }
    }
}
