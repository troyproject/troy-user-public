package com.troy.user.client.util;

import com.troy.commons.dto.out.Res;
import com.troy.user.client.constants.Constants;
import com.troy.user.dto.out.auth.token.TokenResData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * token 缓存实现
 *
 * @author Han
 */
public class TokenCache extends ConcurrentHashMap<String, Res<TokenResData>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCache.class);

    /**
     * 可用的 token 队列
     */
    private DelayQueue<TokenExpiry> activeTokenQueue = new DelayQueue<>();

    public TokenCache() {
        new Thread(new EvictTokenExpiry(), Constants.THREAD_NAME_EVICT_EXPIRY_TOKEN).start();
    }

    /**
     * @param key
     * @param value
     * @return the previous value associated with {@code key}, or
     * {@code null} if there was no mapping for {@code key}* @return
     */
    @Override
    public Res<TokenResData> put(String key, Res<TokenResData> value) {
        if (this.size() > Constants.CACHE_TOKEN_MAX_SIZE) {
            return null;
        }
        //有序，先入缓存队列，再入延时队列
        Res<TokenResData> previous = super.put(key, value);
        this.activeTokenQueue.put(new TokenExpiry(key, TokenExpiryDateUtil.computeExpiryTime(value)));
        return previous;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Res<TokenResData>> resOutMap) {
        if (resOutMap == null || this.size() + resOutMap.size() > Constants.CACHE_TOKEN_MAX_SIZE) {
            return;
        }
        super.putAll(resOutMap);
        long currentTimeMillis = System.currentTimeMillis();
        for (Entry<? extends String, ? extends Res<TokenResData>> entry : resOutMap.entrySet()) {
            this.activeTokenQueue.put(new TokenExpiry(entry.getKey(), TokenExpiryDateUtil.computeExpiryTime(currentTimeMillis, entry.getValue())));
        }
    }

    /**
     * @param key
     * @param resOut
     * @return the previous value associated with the specified key,
     * or {@code null} if there was no mapping for the key
     */
    @Override
    public Res<TokenResData> putIfAbsent(String key, Res<TokenResData> resOut) {
        if (this.size() > Constants.CACHE_TOKEN_MAX_SIZE) {
            return null;
        }
        //有序，先入缓存队列，再入延时队列
        Res<TokenResData> previous = super.putIfAbsent(key, resOut);
        this.activeTokenQueue.put(new TokenExpiry(key, TokenExpiryDateUtil.computeExpiryTime(resOut)));
        return previous;
    }

    /**
     * 驱逐期满的令牌
     */
    private class EvictTokenExpiry implements Runnable {

        @Override
        public void run() {
            try {
                for (; ; ) {
                    TokenExpiry tokenExpiry = TokenCache.this.activeTokenQueue.take();
                    if (tokenExpiry == null) {
                        continue;
                    }
                    TokenCache.this.remove(tokenExpiry.getValue());
                }
            } catch (Exception e) {
                LOGGER.error("Evict expiry token error", e);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e1) {
                    LOGGER.error(e1.getMessage(), e1);
                }
                this.run();
            }
        }
    }

    /**
     * 令牌有效期实例
     */
    private class TokenExpiry implements Delayed {
        /**
         * 对应 TokenCache.key
         */
        private final String value;
        /**
         * 期满时间毫秒数
         */
        private final long expiryTimeMillis;

        public TokenExpiry(String value, long expiryTimeMillis) {
            this.value = value;
            this.expiryTimeMillis = expiryTimeMillis;
        }

        @Override
        public int compareTo(Delayed other) {
            if (this == other) {
                return 0;
            }
            long diff = this.getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS);
            return diff == 0L ? 0 : (diff < 0L ? -1 : 1);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expiryTimeMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
