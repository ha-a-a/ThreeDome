package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/1/11
 * @Desc
 */
@Service
@Slf4j
public class RedisPipelineLuaExampleService {
    @Autowired
    JedisPool jedisPool;
    //拿锁的EVAL函数
    private static final String LUA_SCRIPT_LOCK = "return redis.call('SET', KEYS[1], ARGV[1], 'NX', 'PX', ARGV[2]) ";

    // 释放锁的Lua脚本
    private static final String LUA_SCRIPT_UNLOCK =
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "    redis.call('del', KEYS[1]); " +
                    "    return 'suc' " +
                    "else " +
                    "    return 'fail' " +
                    "end";

    // 添加续期相关的常量
    private static final String LUA_SCRIPT_RENEW =
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "    redis.call('pexpire', KEYS[1], ARGV[2]); " +
                    "    return 'suc' " +
                    "else " +
                    "    return 'fail' " +
                    "end";
    private static final long DEFAULT_LOCK_TIMEOUT = 3000;

    public void lockByPipeline(long batchId, int finalI) throws InterruptedException {
        String key = "op.redis.pipeline.tmy.test." + batchId + "." + finalI;
        String lockValue = "op.redis.pipeline.tmy.test." + batchId + "." + finalI;
        try (Jedis jedis = jedisPool.getResource();
             Pipeline pipeline = jedis.pipelined()) { // Pipeline也会被自动关闭
            Response<String> result = pipeline.eval(LUA_SCRIPT_LOCK,
                    Collections.singletonList(key),
                    Arrays.asList(lockValue, String.valueOf(DEFAULT_LOCK_TIMEOUT))
            );
            pipeline.sync();
            if (result != null && "OK".equals(result.get())) {
                log.info("testPipeline lock suc. key={},pipelined={}", key, pipeline);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSomething(long batchId, int finalI) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "op.redis.normal.tmy.test." + batchId + "." + finalI;
            String set = jedis.set(key, "1", "nx", "ex", 24 * 60 * 60L);
            if (!"OK".equals(set)) {
                return;
            }
            log.info("testPipeline setSomething key={}", key);
        }
    }

    public void getSomething(long batchId, int finalI) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "op.redis.normal.tmy.test." + batchId + "." + finalI;
            String s = jedis.get("op.redis.normal.tmy.test." + finalI);
            log.info("testPipeline getSomething key={}", key);
        }
    }

}