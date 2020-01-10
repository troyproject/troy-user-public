package com.troy.user.dto.converter.token;

import com.troy.user.dto.constants.KeyConstants;
import com.troy.user.dto.converter.AbstractTypeConverter;
import com.troy.user.dto.converter.TypeConverter;
import com.troy.user.dto.out.auth.token.CheckTokenResData;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.Map;
import java.util.Set;

/**
 * description 校验令牌请求出参类型转换器
 *
 * @author Han
 * @date 2018/10/12 11:27
 */
public class CheckTokenResDataConverter extends AbstractTypeConverter<Map<String, ?>, CheckTokenResData> implements TypeConverter<Map<String, ?>, CheckTokenResData> {

    @Override
    public CheckTokenResData convert(Map<String, ?> in) throws Exception {
        if (in == null) {
            return null;
        }
        CheckTokenResData checkTokenResData = new CheckTokenResData();
        checkTokenResData.setGrantType(in.get(AccessTokenConverter.GRANT_TYPE) == null ? null : in.get(AccessTokenConverter.GRANT_TYPE).toString());
        checkTokenResData.setClientId(in.get(AccessTokenConverter.CLIENT_ID) == null ? null : in.get(AccessTokenConverter.CLIENT_ID).toString());
        checkTokenResData.setExpiration(in.get(AccessTokenConverter.EXP) == null ? null : Integer.valueOf(in.get(AccessTokenConverter.EXP).toString()));
        checkTokenResData.setScope((Set<String>) in.get(AccessTokenConverter.SCOPE));
        checkTokenResData.setUserId(in.get(KeyConstants.AUTHENTICATION_USER_ID) == null ? null : Long.valueOf(in.get(KeyConstants.AUTHENTICATION_USER_ID).toString()));
        checkTokenResData.setUsername(in.get(UserAuthenticationConverter.USERNAME) == null ? null : in.get(UserAuthenticationConverter.USERNAME).toString());
        checkTokenResData.setTokenId(in.get(AccessTokenConverter.JTI) == null ? null : in.get(AccessTokenConverter.JTI).toString());
        checkTokenResData.setAccessTokenId(in.get(AccessTokenConverter.ATI) == null ? null : in.get(AccessTokenConverter.ATI).toString());
        return checkTokenResData;
    }
}
