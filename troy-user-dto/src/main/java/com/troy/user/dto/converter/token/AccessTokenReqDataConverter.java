package com.troy.user.dto.converter.token;

import com.troy.user.dto.constants.KeyConstants;
import com.troy.user.dto.converter.AbstractTypeConverter;
import com.troy.user.dto.converter.TypeConverter;
import com.troy.user.dto.in.auth.token.AccessTokenReqData;
import org.springframework.security.oauth2.common.util.OAuth2Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * description 访问令牌请求入参类型转换器
 *
 * @author Han
 * @date 2018/10/12 11:27
 */
public class AccessTokenReqDataConverter extends AbstractTypeConverter<AccessTokenReqData, Map<String, String>> implements TypeConverter<AccessTokenReqData, Map<String, String>> {

    @Override
    public Map<String, String> convert(AccessTokenReqData in) throws Exception {
        if (in == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>(5);
        map.put(OAuth2Utils.GRANT_TYPE, in.getGrantType());
        map.put(OAuth2Utils.SCOPE, OAuth2Utils.formatParameterList(in.getScope()));
        map.put(KeyConstants.AUTHENTICATION_USERNAME, in.getUsername());
        map.put(KeyConstants.AUTHENTICATION_PASSWORD, in.getPassword());
        map.put(KeyConstants.AUTHENTICATION_VERIFICATION_CODE, in.getVerificationCode());
        map.put(KeyConstants.AUTHENTICATION_CHANNEL, in.getChannel() == null ? null : in.getChannel().toString());
        return map;
    }
}
