package com.example.demo.async.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/19
 * @Desc
 */
public interface AsyncService {
    @Async
    void testAsync() throws InterruptedException;
}
