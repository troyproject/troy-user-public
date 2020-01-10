package com.troy.user.service.validator.user;

import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dto.constants.RegisterType;
import com.troy.user.dto.constants.ServiceResState;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
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
public class UserRegisterReqDataValidator implements Validator<UserRegisterReqData> {

    @Override
    public void verify(UserRegisterReqData in) throws VerificationException {
        if (in == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (!Pattern.matches(Constants.USER_PASSWORD_REGEX, in.getPassword())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "password", in.getPassword());
        }
        RegisterType registerType = RegisterType.find(in.getRegisterType());
        if (registerType == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "registerType", in.getRegisterType());
        }
        if (RegisterType.PHONE == registerType) {
            if (StringUtils.isEmpty(in.getPhoneAreaCode()) || StringUtils.isEmpty(in.getPhoneNumber())) {
                throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "phoneAreaCode or phoneNumber");
            }
            if (Pattern.matches(Constants.REGEX_PHONE_AREA_CODE_DOMESTIC, in.getPhoneAreaCode()) && !Pattern.matches(Constants.REGEX_PHONE_DOMESTIC, in.getPhoneNumber())) {
                //国内手机校验
                throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "phoneNumber", in.getPhoneNumber());
            }
            //其它国家地区暂不校验
        } else if (RegisterType.EMAIL == registerType && !Pattern.matches(Constants.REGEX_EMAIL, in.getEmail())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "email", in.getEmail());
        }
        if (RegisterType.EMAIL == registerType && StringUtils.isNotBlank(in.getEmailReferrer())) {
            if (!Pattern.matches(Constants.REGEX_EMAIL, in.getEmailReferrer())) {
                throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "email", in.getEmail());
            }
            if (in.getEmail().equals(in.getEmailReferrer())) {
                throw new VerificationException(ServiceResState.FAIL_EMAIL_SAME, "email");
            }
        }
    }
}
