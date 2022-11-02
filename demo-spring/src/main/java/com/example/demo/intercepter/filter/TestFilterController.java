package com.example.demo.intercepter.filter;

import com.example.demo.intercepter.interceptor.CustomMethodAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 19:26
 */
@RestController
@RequestMapping("/filter")
public class TestFilterController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "欢迎进入首页";
    }

    @RequestMapping("/noRoot")
    public String noRoot() {
        return "对不起,请先登录";
    }

    @RequestMapping("/one")
    public String one() {
        return "one";
    }

    @RequestMapping("/two")
    public String two() {
        return "two";
    }

    @RequestMapping("/three")
    public String three() {
        return "three";
    }

    @RequestMapping("/four")
    public String four() {
        return "four";
    }

    @RequestMapping("/five")
    @CustomMethodAnnotation(name = "give me five")
    public String five() {
        return "interceptor";
    }

}
