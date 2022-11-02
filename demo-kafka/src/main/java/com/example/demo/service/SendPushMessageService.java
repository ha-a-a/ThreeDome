package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.PushMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/5/22
 * @Desc
 */
@Slf4j
@Service
public class SendPushMessageService {
    @Value("${spring.kafka.remote.topic}")
    private String remoteTopic;
    @Autowired
    @Qualifier("remoteKafkaProducer")
    private KafkaTemplate remoteKafkaTemplate;

    public String splicePushMsg(String content) {
        log.info("splicePushMsg begin!");
        long beginTime = System.currentTimeMillis();
        PushMessage pushMessage = JSONObject.parseObject(content, PushMessage.class);
        List<String> targetList = pushMessage.getTargetList();
        if (targetList.size() > 400) {
            List<String> cidList = new ArrayList<>();
            for (int i = 0; i < targetList.size(); i++) {
                cidList.add(targetList.get(i));
                if (cidList.size() == 400 && i == targetList.size() - 1) {
                    pushMessage.setTargetList(cidList);
//                    remoteKafkaTemplate.send(remoteTopic, pushMessage);
                    cidList.clear();
                }
            }
        } else {
//            remoteKafkaTemplate.send(remoteTopic, pushMessage);
        }
        log.info("splicePushMsg end! cost time:{}", System.currentTimeMillis() - beginTime);
        return content;
    }

}
