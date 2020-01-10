package com.troy.user.service.validator.auth;

import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dto.constants.GrantType;
import com.troy.user.dto.constants.YesOrNo;
import com.troy.user.dto.in.auth.client.AuthClientAddReqData;
import com.troy.user.service.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * description: todo
 *
 * @author Han
 * @date 2019-01-16 16:31
 */
@Component
public class AuthClientAddReqDataValidator implements Validator<AuthClientAddReqData> {

    @Override
    public void verify(AuthClientAddReqData in) throws VerificationException {
        if (in == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (in.getEnable() == null || YesOrNo.find(in.getEnable()) == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "enable", in.getEnable());
        }
        if (StringUtils.isEmpty(in.getClientId())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "clientId", in.getClientId());
        }
        if (StringUtils.isEmpty(in.getSecret())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "secret", in.getSecret());
        }
        if (in.getAccessTokenValidity() == null || in.getAccessTokenValidity() < 1) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "accessTokenValidity", in.getAccessTokenValidity());
        }
        if (in.getRefreshTokenValidity() == null || in.getRefreshTokenValidity() < 1) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "refreshTokenValidity", in.getRefreshTokenValidity());
        }
        if (in.getGrantTypeSet() == null || in.getGrantTypeSet().isEmpty()) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "grantTypeSet");
        }
        for (String type : in.getGrantTypeSet()) {
            if (GrantType.find(type) == null) {
                throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "grantTypeSet");
            }
        }
    }
}
