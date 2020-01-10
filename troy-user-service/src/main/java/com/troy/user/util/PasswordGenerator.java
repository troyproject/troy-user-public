package com.troy.user.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 密码生成器
 */
public class PasswordGenerator {

    public static String generate(String password, Object salt) {
        return new Md5PasswordEncoder().encodePassword(password, salt);
    }
}