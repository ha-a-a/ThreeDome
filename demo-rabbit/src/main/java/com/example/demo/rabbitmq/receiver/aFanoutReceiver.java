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
@RabbitListener(queues = "fanout.A")
public class aFanoutReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("fanout.A Receiver : " + hello);
    }

}
