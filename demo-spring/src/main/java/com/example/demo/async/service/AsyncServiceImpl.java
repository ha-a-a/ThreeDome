package com.example.demo.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/19
 * @Desc
 */
@Service
public class AsyncServiceImpl implements AsyncService{
    public static final Logger LOGGER= LoggerFactory.getLogger(AsyncServiceImpl.class);
    @Async
    @Override
    public void testAsync() throws InterruptedException {
        LOGGER.info("com.example.demo.async task start at:{}",System.currentTimeMillis());
        Thread.sleep(5000);
        int i=1/0;//AsyncConfig中getAsyncUncaughtExceptionHandler()方法可以捕获异常
        LOGGER.info("com.example.demo.async task end at:{}",System.currentTimeMillis());
    }
}
