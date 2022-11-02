package com.example.demo.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/13
 * @Desc
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello" + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context, (RabbitTemplate.ConfirmCallback) (correlationData, ack, cause) -> {
            if (!ack){
                // 发送失败
            }
        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object recv = rabbitTemplate.receiveAndConvert("hello");
        if (null != recv)
            System.out.println("recv : " + recv.toString());
        System.out.println("recv : " + recv);
    }

    public void oneToMany(Integer num) {
        String context = "springToMany " + num;
        System.out.println("Sender:" + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void publish() {
        String context = "hi, fanout msg ";
        System.out.println("Sender:" + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "anyone", context);
    }

    public void sendTopicMessage() {
        String context = "hi, topic msg ";
        System.out.println("sendTopicMessage:" + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context);
    }

    public void sendTopicMessages() {
        String context = "hi, topic msgs ";
        System.out.println("sendTopicMessages:" + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", context);
    }
}
