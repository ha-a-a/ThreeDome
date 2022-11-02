package com.example.demo.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/5/22
 * @Desc
 */
@Component
@Slf4j
public class SenderService {

    @Autowired
    @Qualifier("localKafkaProducer")
    private KafkaTemplate localKafkaTemplate;

    @Autowired
    @Qualifier("remoteKafkaProducer")
    private KafkaTemplate remoteKafkaTemplate;

    @Value("${spring.kafka.local.topic}")
    private String localTopic;

    @Value("${spring.kafka.remote.topic}")
    private String remoteLocal;

    public void localSend(String msg){
        log.info("kafka send local msg:{} begin",msg);
        localKafkaTemplate.send(localTopic,msg);
        log.info("kafka send local msg:{} end",msg);
    }
    public void remoteSend(String msg){
        log.info("kafka send remote msg:{} begin",msg);
        remoteKafkaTemplate.send(remoteLocal,msg);
        log.info("kafka send remote msg:{} end",msg);
    }
}
