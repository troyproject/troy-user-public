package com.troy.user.web.config;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date 2018-12-20 下午4:57:42
 * @copyright
 */
@Configuration
public class SpringBootShutdown {

    @Bean
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }

    @Bean
    public EmbeddedServletContainerCustomizer tomcatCustomizer() {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                ((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers(gracefulShutdown());
            }
        };
    }

    private static class GracefulShutdown
            implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

        private static final Logger LOGGER = LoggerFactory.getLogger(GracefulShutdown.class);

        private volatile Connector connector;

        private boolean isStop = false;

        @Override
        public void customize(Connector connector) {
            this.connector = connector;
        }

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {

            // 有多个地方会发送ContextClosedEvent事件,这里就接收最先到达的请求就行
            synchronized (this) {
                if (isStop) {
                    return;
                }
                LOGGER.info("开始暂停应用......");
                this.connector.pause();
                isStop = true;
            }

            Executor executor = this.connector.getProtocolHandler().getExecutor();

            if (executor instanceof ThreadPoolExecutor) {
                try {
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                    threadPoolExecutor.shutdown();
                    //线程退出有2种方式,各位根据实际情况选择
                    //1,等待线程执行完毕后退出
                    while (!threadPoolExecutor.isTerminated()) {
                        TimeUnit.MILLISECONDS.sleep(200);
                    }
                    LOGGER.info("Shut down done!");
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    LOGGER.error("停止应用线程中断", ex);
                }
            }
        }

    }

}