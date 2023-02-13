package com.example.demoneo4j.interfaces.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tangmengyue
 * @ClassName TestControllerTest.java
 * @Description TODO
 * @createTime 2023年02月09日 16:31:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class TestControllerTest {

    @Test
    public void cost() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10; i++) {
            final int count = i;
            new Thread(() -> {
                log.info("start.....count={}", count);
                int time = count * 1000;
                ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/neo4j-demo/test/cost?time=" + time + "&operate=ttttt", String.class, Collections.emptyMap());
                String body = forEntity.getBody();

                log.info("count={},body={}", count, body);
            }).start();
        }
        Thread.sleep(10000);
        log.info("ddddddd");
    }
}