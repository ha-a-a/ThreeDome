package com.example.demoneo4j.interfaces.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tangmengyue
 * @ClassName MovieController.java
 * @Description TODO
 * @createTime 2023年01月30日 10:50:00
 */
@RestController
@RequestMapping("/response/async")
@Slf4j
public class AsyncController {

    // 异步请求
    @GetMapping("/callable")
    public Callable<String> asyncCallable(@RequestParam Integer time, @RequestParam String operate) {
        log.info("async callable start time={}", time);
        return () -> {
            Thread.sleep(time);
            log.info("cost={}, operate={}", time, operate);
            return "callable";
        };
    }
    public static ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(30);

    @RequestMapping("/deferredResult")
    public DeferredResult<String> deferredResult(){
        log.info("外部线程：" + Thread.currentThread().getName());
        //设置超时时间
        DeferredResult<String> result = new DeferredResult<String>(60*1000L);
        //处理超时事件 采用委托机制
        result.onTimeout(new Runnable() {

            @Override
            public void run() {
                log.error("DeferredResult超时");
                result.setResult("超时了!");
            }
        });
        result.onCompletion(new Runnable() {

            @Override
            public void run() {
                //完成后
                log.info("调用完成");
            }
        });
        FIXED_THREAD_POOL.execute(new Runnable() {

            @Override
            public void run() {
                //处理业务逻辑
                log.info("内部线程：" + Thread.currentThread().getName());
                //返回结果
                result.setResult("DeferredResult!!");
            }
        });
        return result;
    }

}