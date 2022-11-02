package com.example.demo.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/8/14
 * @Desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class SenderServiceTest {

    @Autowired
    @Qualifier("localKafkaProducer")
    private KafkaTemplate localKafkaTemplate;

    @Autowired
    @Qualifier("remoteKafkaProducer")
    private KafkaTemplate remoteKafkaTemplate;

    @Value("${spring.kafka.local.topic}")
    private String localTopic;

    @Value("${spring.kafka.remote.topic}")
    private String remoteTopic;
    @Test
    public void send20wCidToLocal() {
        JSONObject jsonObject = new JSONObject();
        localKafkaTemplate.send(localTopic, jsonObject.toJSONString());
    }

    @Test
    public void send20wCidToRemote() throws Exception {
//        for (int i = 0; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            remoteKafkaTemplate.send(remoteTopic, jsonObject.toJSONString());
//        }
    }
}