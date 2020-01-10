package com.troy.user.dto.converter.token;

import com.troy.user.dto.constants.KeyConstants;
import com.troy.user.dto.converter.AbstractTypeConverter;
import com.troy.user.dto.converter.TypeConverter;
import com.troy.user.dto.out.auth.token.TokenKeyResData;

import java.util.Map;

/**
 * description 令牌公钥请求出参类型转换器
 *
 * @author Han
 * @date 2018/10/12 11:27
 */
public class TokenKeyResDataConverter extends AbstractTypeConverter<Map<String, String>, TokenKeyResData> implements TypeConverter<Map<String, String>, TokenKeyResData> {

    @Override
    public TokenKeyResData convert(Map<String, String> in) throws Exception {
        if (in == null) {
            return null;
        }
        TokenKeyResData tokenKeyResData = new TokenKeyResData();
        tokenKeyResData.setAlg(in.get(KeyConstants.RESPONSE_TOKEN_KEY_ALG));
        tokenKeyResData.setValue(in.get(KeyConstants.RESPONSE_TOKEN_KEY_VALUE));
        return tokenKeyResData;
    }
}
