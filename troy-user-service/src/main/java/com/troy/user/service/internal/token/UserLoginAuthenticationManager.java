package com.troy.user.service.internal.token;

import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dto.constants.KeyConstants;
import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.out.user.UserDetails;
import com.troy.user.service.internal.user.UserLoginLogService;
import com.troy.user.service.internal.user.UserService;
import com.troy.user.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 用户登录使用的认证器
 *
 * @author Han
 * @date 2018-11-14 10:04
 */
@Component
public class UserLoginAuthenticationManager implements AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginAuthenticationManager.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new UsernameNotFoundException("The principal is null");
        }
        if (authentication.getCredentials() == null) {
            throw new AuthenticationCredentialsNotFoundException("The credentials is null");
        }
        Map<String, String> requestDetails = (Map<String, String>) authentication.getDetails();
        if (requestDetails == null || StringUtils.isEmpty(requestDetails.get(KeyConstants.AUTHENTICATION_VERIFICATION_CODE))) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, KeyConstants.AUTHENTICATION_VERIFICATION_CODE);
        }
        String credentials = authentication.getCredentials().toString();
        try {
            if (StringUtils.isNotEmpty(credentials)) {
                credentials = this.securityUtil.decrypt(credentials);
            }
            UserDetails userDetails = this.userService.loadUserByLoginUsername(authentication.getPrincipal().toString());
            this.userService.checkUser(userDetails, credentials);
            //验证码
            String verificationCode = requestDetails.get(KeyConstants.AUTHENTICATION_VERIFICATION_CODE);
            this.userService.checkUserSafetyMeasures(userDetails, verificationCode, verificationCode, verificationCode, TextMessageType.LOGIN, true);
            String ip = requestDetails.get(KeyConstants.AUTHENTICATION_IP);
            Integer channel = null;
            if (requestDetails.get(KeyConstants.AUTHENTICATION_CHANNEL) != null) {
                try {
                    channel = Integer.parseInt(requestDetails.get(KeyConstants.AUTHENTICATION_CHANNEL));
                } catch (NumberFormatException e) {
                    LOGGER.error("Failed to save user login log,Channel[{}] is invalid", channel);
                }
            }
            this.userLoginLogService.save(ip, channel, authentication.getPrincipal().toString(), userDetails);
            Map<String, Object> responseDetails = new HashMap<>(1);
            responseDetails.put(com.troy.user.dto.constants.KeyConstants.AUTHENTICATION_USER_ID, userDetails.getUserId());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal().toString(), credentials, null);
            usernamePasswordAuthenticationToken.setDetails(responseDetails);
            return usernamePasswordAuthenticationToken;
        } catch (AuthenticationException e) {
            throw e;
        }catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }
}