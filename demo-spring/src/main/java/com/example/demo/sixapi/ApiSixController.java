package com.example.demo.sixapi;

import com.example.demo.intercepter.interceptor.CustomMethodAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("ip/get")
    public String getIp() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }
}
