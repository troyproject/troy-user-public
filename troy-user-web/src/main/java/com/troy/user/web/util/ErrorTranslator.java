package com.troy.user.web.util;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.exception.configuration.ConfigurationException;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * description 错误翻译器
 *
 * @author Han
 * @date 2018-10-12 17:10
 */
public class ErrorTranslator {

    /**
     * description 解析异常
     *
     * @author Han
     * @date 2018/10/10 18:45
     */
    public static <RDO extends ResData> Res<RDO> translate(Throwable throwable) {
        if (throwable == null) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT);
        }
        if (VerificationException.class.isInstance(throwable)) {
            VerificationException exception = (VerificationException) throwable;
            return ResFactory.getInstance().createRes(exception.getState());
        } else if (ConfigurationException.class.isInstance(throwable)) {
            ConfigurationException exception = (ConfigurationException) throwable;
            return ResFactory.getInstance().createRes(exception.getState(), exception.getMessage());
        } else if (ServiceException.class.isInstance(throwable)) {
            ServiceException exception = (ServiceException) throwable;
            return ResFactory.getInstance().createRes(exception.getStateTypeSuper(), exception.getDepictDetailed());
        } else if (HttpRequestMethodNotSupportedException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_REQUEST, throwable.getMessage());
        } else if (UnauthorizedUserException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USER);
        } else if (DisabledException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USER_DISABLED);
        } else if (UsernameNotFoundException.class.isInstance(throwable)) {
            UsernameNotFoundException exception = (UsernameNotFoundException) throwable;
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USERNAME_NOT_FOUND, exception.getMessage());
        } else if (BadCredentialsException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USER_BAD_CREDENTIALS);
        } else if (AuthenticationException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USER);
        } else if (InvalidGrantException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USER);
        } else if (InvalidTokenException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_USER_BAD_CREDENTIALS);
        } else if (ClientAuthenticationException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_AUTH_CLIENT);
        } else if (DuplicateKeyException.class.isInstance(throwable) || MySQLIntegrityConstraintViolationException.class.isInstance(throwable) || com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException.class.isInstance(throwable)) {
            return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_SERVICE_REPEATED);
        }
        if (throwable.getCause() != null) {
            return translate(throwable.getCause());
        }
        return ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT);
    }
}
