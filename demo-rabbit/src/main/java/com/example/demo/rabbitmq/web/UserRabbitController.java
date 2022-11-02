package com.example.demo.rabbitmq.web;

import com.example.demo.common.RestResult;
import com.example.demo.common.RestResultGenerator;
import com.example.demo.rabbitmq.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRabbitController {
    @Autowired
    private Sender helloSender;

    @GetMapping("/test/path")
    public String hello() {
        return "hello world!";
    }

    @PostMapping("/test/helloWorld/sendMsg")
    public RestResult sendMsg() {
        helloSender.send();
        return RestResultGenerator.genSuccessResult();
    }

    @PostMapping("/test/oneToMany/sendMsg")
    public RestResult oneToMany() {
        for (int i = 0; i < 300; i++) {
            helloSender.oneToMany(i);
        }
        return RestResultGenerator.genSuccessResult();
    }

    @PostMapping("/test/fanoutMsg/sendMsg")
    public RestResult sendFanoutMsg() {
        helloSender.publish();
        return RestResultGenerator.genSuccessResult();
    }
    @PostMapping("/test/topicMsg/sendMsg")
    public RestResult sengTopicMsg(){
        helloSender.sendTopicMessage();
        helloSender.sendTopicMessages();
        return RestResultGenerator.genSuccessResult();
    }
}
