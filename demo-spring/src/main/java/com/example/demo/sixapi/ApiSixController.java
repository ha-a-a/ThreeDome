package com.example.demo.sixapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: desc
 * @author: mengyuetang
 * @email:
 * @date: 2021/11/9 19:26
 */
@RestController
@RequestMapping("/api")
public class ApiSixController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "欢迎进入首页";
    }

    // 负载均衡
    @RequestMapping("/ip/get")
    public String getIp() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }

    // 使用consumer鉴权
    @RequestMapping("/consumer/auth")
    public String auth(@RequestParam String name) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress() + " welcome to " + name;
    }
    // 自定义插件
    @RequestMapping("/plugin/custom")
    public String customPlugin(@RequestParam String name) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress() + " transfer to" + name;
    }
}
