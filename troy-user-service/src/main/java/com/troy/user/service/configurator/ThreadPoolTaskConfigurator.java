package com.troy.user.service.configurator;

import com.troy.user.service.configurator.properties.LoggerThreadPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description: 线程池配置
 *
 * @author Han Email：youhuihan@xinhe99.com
 * @date 2019-05-09 14:56
 */
@Configuration
public class ThreadPoolTaskConfigurator {

    @Autowired
    private LoggerThreadPoolProperties loggerThreadPoolProperties;

    /**
     * 配置错误消息异步处理器
     *
     * @return
     */
    @Bean("loggerExecutor")
    Executor createErrorMessageExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setDaemon(true);
        executor.setCorePoolSize(this.loggerThreadPoolProperties.getCoreSize());
        executor.setMaxPoolSize(this.loggerThreadPoolProperties.getMaxSize());
        executor.setKeepAliveSeconds(this.loggerThreadPoolProperties.getKeepAliveSeconds());
        //#当最小的线程数已经被占用满后，新的任务会被放进queue里面，当这个queue的capacity也被占满之后，pool就会创建新线程处理这个任务，直到总线程数达到了max size，之后的任务会被拒绝并抛出TaskRejectedException异常（缺省配置的情况下，可以通过rejection-policy来决定如何处理这种情况）。缺省值为：Integer.MAX_VALUE
        executor.setQueueCapacity(this.loggerThreadPoolProperties.getQueueCapacity());
        executor.setThreadNamePrefix(LoggerThreadPoolProperties.THREAD_NAME_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
        return executor;
    }
}
