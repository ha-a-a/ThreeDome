package com.example.demo.consumer;

import com.example.demo.service.SendPushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/8/17
 * @Desc
 */
@Component
@Slf4j
public class Consumer {
    @Autowired
    private SendPushMessageService sendPushMessageService;

    @KafkaListener(topics = "${spring.kafka.local.topic}", containerFactory = "localKafkaConsumerListenerFactory")
    public void localProcess(@Payload String message) {
        log.info("receive local msg: {}", message);
        sendPushMessageService.splicePushMsg(message);
    }

//    @KafkaListener(topics = "${spring.kafka.local.topic}", containerFactory = "remoteKafkaConsumerListenerFactory")
//    public void remoteProcess(@Payload String message) {
//        log.info("receive remote msg: {}", message);
//    }
}
