package com.troy.user.service.internal.token;

import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.user.dto.constants.KeyConstants;
import com.troy.user.dto.constants.YesOrNo;
import com.troy.user.dto.out.user.UserDetails;
import com.troy.user.service.internal.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 刷新令牌使用的认证器
 *
 * @author Han
 * @date 2018-11-14 10:04
 */
public class RefreshTokenAuthenticationManager implements AuthenticationManager {

    private UserService userService;

    public RefreshTokenAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new UsernameNotFoundException("The principal is null");
        }
        if (Authentication.class.isInstance(authentication.getPrincipal())) {
            authentication = (Authentication) authentication.getPrincipal();
        }
        try {
            UserDetails userDetails = this.userService.loadUserByLoginUsername(authentication.getPrincipal().toString());
            if (userDetails == null) {
                throw new UsernameNotFoundException("loginUsername=" + authentication.getPrincipal().toString());
            }
            if (userDetails.getStatus() != null && userDetails.getStatus().intValue() == YesOrNo.NO.getCode()) {
                throw new ServiceException(StateTypeSuper.FAIL_AUTH_USER_DISABLED);
            }
            Map<String, Object> responseDetails = new HashMap<>(1);
            responseDetails.put(KeyConstants.AUTHENTICATION_USER_ID, userDetails.getUserId());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal().toString(), authentication.getCredentials().toString(), null);
            usernamePasswordAuthenticationToken.setDetails(responseDetails);
            return usernamePasswordAuthenticationToken;
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }
}
