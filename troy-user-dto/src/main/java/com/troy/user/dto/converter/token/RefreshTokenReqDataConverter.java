package com.troy.user.dto.converter.token;

import com.troy.user.dto.constants.GrantType;
import com.troy.user.dto.constants.KeyConstants;
import com.troy.user.dto.converter.AbstractTypeConverter;
import com.troy.user.dto.converter.TypeConverter;
import com.troy.user.dto.in.auth.token.RefreshTokenReqData;
import org.springframework.security.oauth2.common.util.OAuth2Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * description 刷新令牌请求入参类型转换器
 *
 * @author Han
 * @date 2018/10/12 11:27
 */
public class RefreshTokenReqDataConverter extends AbstractTypeConverter<RefreshTokenReqData, Map<String, String>> implements TypeConverter<RefreshTokenReqData, Map<String, String>> {

    @Override
    public Map<String, String> convert(RefreshTokenReqData in) throws Exception {
        if (in == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>(3);
        map.put(OAuth2Utils.GRANT_TYPE, GrantType.REFRESH_TOKEN.getCode());
        map.put(OAuth2Utils.SCOPE, OAuth2Utils.formatParameterList(in.getScope()));
        map.put(KeyConstants.REQUEST_TOKEN_PARAMETER_REFRESH_TOKEN, in.getRefreshToken());
        return map;
    }
}
