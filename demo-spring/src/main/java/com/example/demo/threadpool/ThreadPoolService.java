package com.example.demo.threadpool;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/12/26
 * @Desc
 */
public class ThreadPoolService {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new DefaultThreadFactory("tmy-pool");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                5, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(15), threadFactory
                , new ThreadPoolExecutor.AbortPolicy()
        );
        // 只能接受20个任务,按理说应该能接收最多5+15个任务
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new TaskRunner(i));
        }
    }
}
