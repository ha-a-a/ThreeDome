package com.example.demo.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/13
 * @Desc
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver2 {
    @RabbitHandler
    public void handler(String hello) {
        System.out.println("Receiver2 : " + hello);
    }

}
