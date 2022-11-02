package com.example.demo.enumdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC 测试tomcat最大连接数
 * @Author mengyuetang
 * @createTime 2019/6/18
 * @Desc
 */
@RestController
@RequestMapping("/tomcat/test")
@Slf4j
public class TomcatController {
    @GetMapping("get")
    public String get() throws InterruptedException {
        log.info("tomcat connect one times");
        Thread.sleep(10000);
        return "connect";
    }
}
