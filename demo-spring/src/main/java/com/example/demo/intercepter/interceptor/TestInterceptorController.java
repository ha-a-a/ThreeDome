package com.example.demo.intercepter.interceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 19:26
 */
@RestController
@RequestMapping("/interceptor")
public class TestInterceptorController {
    /**
     * no filter路径,测试方法拦截器
     *
     * @return
     */
    @RequestMapping("/five")
    @CustomMethodAnnotation(name = "give me five")
    public String five() {
        return "interceptor";
    }

}
