package com.troy.user.client.httpclient.apache;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * 清理无效连接的线程
 *
 * @author dp
 */
public class IdleConnectionEvictor extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdleConnectionEvictor.class);
    private final HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
        super.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    LOGGER.info("HttpClient清理无效链接的线程");
                    // 关闭失效的连接、关闭空闲超过30秒的连接
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException ex) {
            // 结束
            LOGGER.error("关闭失效的连接，失败");
        }
    }

    /**
     * 关闭清理无效连接的线程
     */
    @PreDestroy
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
