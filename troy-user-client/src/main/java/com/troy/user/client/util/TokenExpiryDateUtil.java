package com.troy.user.client.util;

import com.troy.commons.dto.out.Res;
import com.troy.user.client.constants.Constants;
import com.troy.user.dto.out.auth.token.TokenResData;

/**
 * @author Han
 */
public class TokenExpiryDateUtil {

    /**
     * 判断是否失效
     *
     * @param expiryTimeMillis 到期时间毫秒数
     * @return
     */
    public static boolean isExpiry(long expiryTimeMillis) {
        return isExpiry(System.currentTimeMillis(), expiryTimeMillis);
    }

    /**
     * 判断是否失效
     *
     * @param currentTimeMillis 当前时间毫秒数
     * @param expiryTimeMillis  到期时间毫秒数
     * @return
     */
    public static boolean isExpiry(long currentTimeMillis, long expiryTimeMillis) {
        return currentTimeMillis >= expiryTimeMillis;
    }

    /**
     * 计算 token 失效时间毫秒数
     *
     * @param token
     * @return
     */
    public static long computeExpiryTime(Res<TokenResData> token) {
        return computeExpiryTime(System.currentTimeMillis(), token);
    }

    /**
     * 计算 token 失效时间毫秒数
     *
     * @param token
     * @return
     */
    public static long computeExpiryTime(long currentTimeMillis, Res<TokenResData> token) {
        if (token == null || token.getData() == null || token.getData().getExpiresIn() == null) {
            return -1L;
        }
        int expiresMilliseconds = token.getData().getExpiresIn() * 1000;
        return computeExpiryTime(currentTimeMillis, expiresMilliseconds);
    }

    /**
     * 计算 token 失效时间毫秒数
     *
     * @param expiresMilliseconds 距离到期毫秒数
     * @return
     */
    public static long computeExpiryTime(int expiresMilliseconds) {
        return computeExpiryTime(System.currentTimeMillis(), expiresMilliseconds);
    }

    /**
     * 计算 token 失效时间毫秒数
     *
     * @param currentTimeMillis   当前时间毫秒数
     * @param expiresMilliseconds 距离到期毫秒数
     * @return
     */
    public static long computeExpiryTime(long currentTimeMillis, int expiresMilliseconds) {
        return currentTimeMillis + expiresMilliseconds - Constants.CACHE_TOKEN_EXPIRES_THRESHOLD_MILLISECONDS;
    }
}
