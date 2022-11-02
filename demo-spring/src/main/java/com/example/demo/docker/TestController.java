package com.example.demo.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/22
 * @Desc
 */
@RestController
@RequestMapping("/docker")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "hello docker";
    }
}
