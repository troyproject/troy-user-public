package com.troy.user.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

class AESCipher {

    private static final String SECRET_KEY = "AES";
    private static final String SECURE_RANDOM = "SHA1PRNG";
    private static final String CIPHER_AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String CIPHER_AES_ECB = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return String
     */
    public static String encrypt(String content, String password, byte[] iv) throws Exception {
        // KeyGenerator提供对策密钥生成器的功能,支持各种算法
        KeyGenerator keyGenerator = KeyGenerator.getInstance(SECRET_KEY);
        SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM);
        secureRandom.setSeed(password.getBytes());
        keyGenerator.init(128, secureRandom);
        // SecretKey 负责保存对称密钥
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, SECRET_KEY);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC);
        // 初始化
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        // 加密
        byte[] result = cipher.doFinal(content.getBytes());
        return Base64.encodeBase64URLSafeString(result);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return String
     */
    public static String decrypt(String content, String password, byte[] iv) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(SECRET_KEY);
        SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM);
        secureRandom.setSeed(password.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, SECRET_KEY);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC);
        // 初始化
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        // 加密
        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
        return new String(result);
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return String
     */
    public static String decrypt(String content, String password) throws Exception {
        SecretKeySpec key = new SecretKeySpec(password.getBytes(), SECRET_KEY);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(CIPHER_AES_ECB);
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
        // 加密
        return new String(result);
    }
}
