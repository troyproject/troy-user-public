package com.troy.user.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Han
 */
@Component
public class SecurityUtil {

    @Value("${troy.user.security.key}")
    private String key;

    public String decrypt(String content) throws Exception {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return AESCipher.decrypt(content, this.key);
    }
}
