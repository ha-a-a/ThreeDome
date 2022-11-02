package com.example.demo.async.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 异步任务支持
 * @Author mengyuetang
 * @createTime 2019/3/19
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private static Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    @Value("${com.example.demo.async.task.name:Default-Async-Task}")
    String name;

    @Value("${com.example.demo.async.task.core-pool-size:2}")
    Integer corePoolSize;

    @Value("${com.example.demo.async.task.max-pool-size:4}")
    Integer maxPoolSize;

    @Value("${com.example.demo.async.task.queue-capacity:4}")
    Integer queueCapacity;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(name);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable throwable, Method method, Object... objects) -> {
            logger.error("AsyncExecutors error.{}", throwable.getMessage());
            logger.error("AsyncExecutors error.{}", method.getName());
        };
    }
}
