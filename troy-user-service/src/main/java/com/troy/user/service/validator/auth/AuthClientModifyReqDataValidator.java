package com.troy.user.service.validator.auth;

import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dto.constants.GrantType;
import com.troy.user.dto.constants.YesOrNo;
import com.troy.user.dto.in.auth.client.AuthClientModifyReqData;
import com.troy.user.service.validator.Validator;
import org.springframework.stereotype.Component;

/**
 * description: todo
 *
 * @author Han
 * @date 2019-01-16 16:31
 */
@Component
public class AuthClientModifyReqDataValidator implements Validator<AuthClientModifyReqData> {

    @Override
    public void verify(AuthClientModifyReqData in) throws VerificationException {
        if (in == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (in.getId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "id");
        }
        if (in.getEnable() != null && YesOrNo.find(in.getEnable()) == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "enable", in.getEnable());
        }
        if (in.getAccessTokenValidity() != null && in.getAccessTokenValidity() < 1) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "accessTokenValidity", in.getAccessTokenValidity());
        }
        if (in.getRefreshTokenValidity() != null && in.getRefreshTokenValidity() < 1) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "refreshTokenValidity", in.getRefreshTokenValidity());
        }
        if (in.getGrantTypeSet() != null) {
            if (in.getGrantTypeSet().isEmpty()) {
                throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "grantTypeSet");
            }
            for (String type : in.getGrantTypeSet()) {
                if (GrantType.find(type) == null) {
                    throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "grantTypeSet");
                }
            }
        }
    }
}
