package com.troy.user.dto.converter.token;

import com.troy.user.dto.constants.KeyConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description 定制用户凭证转换器
 *
 * @author Han
 * @date 2018-10-16 15:59
 */
public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        Map<String, Object> response = new LinkedHashMap();
        response.put(UserAuthenticationConverter.USERNAME, authentication.getName());
        Map<String, Object> param = (Map<String, Object>) authentication.getDetails();
        if (param != null) {
            String userId = param.get(KeyConstants.AUTHENTICATION_USER_ID) == null ? null : param.get(KeyConstants.AUTHENTICATION_USER_ID).toString();
            response.put(KeyConstants.AUTHENTICATION_USER_ID, userId);
//            String registerApp = param.get(KeyConstants.AUTHENTICATION_DETAILS_REGISTER_APP) == null ? null : param.get(KeyConstants.AUTHENTICATION_DETAILS_REGISTER_APP).toString();
//            response.put(KeyConstants.AUTHENTICATION_DETAILS_REGISTER_APP, registerApp);
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        Object principal = map.get(UserAuthenticationConverter.USERNAME);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, "N/A", null);
        usernamePasswordAuthenticationToken.setDetails(map);
        return usernamePasswordAuthenticationToken;
    }
}
