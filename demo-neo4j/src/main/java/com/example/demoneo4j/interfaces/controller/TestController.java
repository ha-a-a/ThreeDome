package com.example.demoneo4j.interfaces.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
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
@RequestMapping("/test")
@Slf4j
public class TestController {
    // 两个请求是共用一个servlet线程
    @GetMapping("/cost")
    public String cost(@RequestParam Integer time, @RequestParam String operate) throws InterruptedException {
        Thread.sleep(time);
        log.info("cost={}, operate={}", time, operate);
        return "success";
    }
}