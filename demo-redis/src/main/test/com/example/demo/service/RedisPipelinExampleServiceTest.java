package com.example.demo.service;

import com.example.demo.RedisApplicationTest;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Response;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tangmengyue
 * @version 1.0.0
 * @ClassName RedisPipelinExampleServiceTest.java
 * @Description TODO
 * @createTime 2025-09-02 18:32:00
 */
@Slf4j
public class RedisPipelinExampleServiceTest extends RedisApplicationTest {

    @Autowired
    RedisPipelinExampleService redisPipelinExampleService;
    @Autowired
    RedisPipelineLuaExampleService luaExampleService;

    @Test
    public void testPipelineCommand() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        long batchId = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            String key = "op.redis.pipeline.tmy.test." + batchId + "." + finalI;
            executorService.execute(() -> {
                redisPipelinExampleService.pipelineCommand(key);
                redisPipelinExampleService.pipelineCommand(key);
            });
        }
        Thread.sleep(100000);
        log.info("testPipelineCommand finish done.......");
    }

    @Test
    public void testPipelineCommandError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        long batchId = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            String key = "op.redis.pipeline.tmy.test." + batchId + "." + finalI;
            executorService.execute(() -> {
                redisPipelinExampleService.pipelineCommandError(key);
                redisPipelinExampleService.pipelineCommandError(key);
            });
        }
        Thread.sleep(100000);
        log.info("testPipelineCommandError finish done.......");
    }

    @Test
    public void testMultiCommand() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        long batchId = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            String key = "op.redis.pipeline.tmy.test." + batchId + "." + finalI;
            String normalKey = "op.redis.normal.tmy.test." + batchId + "." + finalI;
            executorService.execute(() -> {
                redisPipelinExampleService.pipelineCommand(key);
                redisPipelinExampleService.normalCommand(normalKey);
            });
        }
        Thread.sleep(100000);
        log.info("testMultiCommand finish done.......");
    }
    @Test
    public void testMultiCommandError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        long batchId = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            String key = "op.redis.pipeline.tmy.test." + batchId + "." + finalI;
            String normalKey = "op.redis.normal.tmy.test." + batchId + "." + finalI;
            executorService.execute(() -> {
                redisPipelinExampleService.pipelineCommandError(key);
                redisPipelinExampleService.normalCommand(normalKey);
            });
        }
        Thread.sleep(100000);
        log.info("testPipelineCommandError finish done.......");
    }
    @Test
    public void testOncePipeline() {
        long batchId = System.currentTimeMillis();
        String key = "op.redis.pipeline.tmy.test." + batchId + ".eee" ;
        redisPipelinExampleService.pipelineCommand(key);

        log.info("testOncePipeline finish done.......");
}
    @Test
    public void testLuaPipeline() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        long batchId = DateTime.now().getMillis();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    luaExampleService.lockByPipeline(batchId, finalI);
                    luaExampleService.setSomething(batchId, finalI);
                    luaExampleService.getSomething(batchId, finalI % 2);
                } catch (Exception e) {
                    log.error("testPipeline error.i={}", finalI, e);
                }
            });
        }
        Thread.sleep(100000);
        log.info("testLuaPipeline finish done.......");
    }
}