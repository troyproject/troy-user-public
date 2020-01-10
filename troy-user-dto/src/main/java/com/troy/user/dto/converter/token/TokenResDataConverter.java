package com.troy.user.dto.converter.token;

import com.troy.user.dto.converter.AbstractTypeConverter;
import com.troy.user.dto.converter.TypeConverter;
import com.troy.user.dto.out.auth.token.TokenResData;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * description 令牌请求出参类型转换器
 *
 * @author Han
 * @date 2018/10/12 11:27
 */
public class TokenResDataConverter extends AbstractTypeConverter<OAuth2AccessToken, TokenResData> implements TypeConverter<OAuth2AccessToken, TokenResData> {

    @Override
    public TokenResData convert(OAuth2AccessToken oAuth2AccessToken) {
        if (oAuth2AccessToken == null) {
            return null;
        }
        TokenResData tokenResData = new TokenResData();
        tokenResData.setAccessToken(oAuth2AccessToken.getValue());
        tokenResData.setRefreshToken(oAuth2AccessToken.getRefreshToken() == null ? null : oAuth2AccessToken.getRefreshToken().getValue());
        tokenResData.setExpiresIn(oAuth2AccessToken.getExpiresIn());
        tokenResData.setScope(oAuth2AccessToken.getScope());
        tokenResData.setTokenType(oAuth2AccessToken.getTokenType());
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        tokenResData.setTokenId(additionalInformation.get(JwtAccessTokenConverter.TOKEN_ID) == null ? null : additionalInformation.get(JwtAccessTokenConverter.TOKEN_ID).toString());
        tokenResData.setAccessTokenId(additionalInformation.get(JwtAccessTokenConverter.ACCESS_TOKEN_ID) == null ? null : additionalInformation.get(JwtAccessTokenConverter.ACCESS_TOKEN_ID).toString());
        return tokenResData;
    }
}
