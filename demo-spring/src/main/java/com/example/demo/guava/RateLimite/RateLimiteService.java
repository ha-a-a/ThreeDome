package com.example.demo.guava.RateLimite;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/12/29
 * @Desc
 */
@Slf4j
public class RateLimiteService {
    private static RateLimiter rateLimiter = RateLimiter.create(50);
    public static void main(String[] args) throws InterruptedException {
        long startTime = ZonedDateTime.now().getSecond();
        for (int i = 0; i < 100; i++) {
            rateLimiter.acquire(20);
            doSomething(i);
        }

        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;
        log.info("elapsedTimeSeconds={}", elapsedTimeSeconds);
    }

    private static void doSomething(int time) throws InterruptedException {
        Thread.sleep(10);
        log.info("doSomething time={}", time);
    }
}
