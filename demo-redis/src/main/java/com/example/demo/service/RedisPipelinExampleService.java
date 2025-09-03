package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.Map;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/4/4
 * @Desc
 */
@Service
public class RedisPipelinExampleService {
    @Autowired
    JedisPool jedisPool;
    public static final Logger LOGGER = LoggerFactory.getLogger(RedisPipelinExampleService.class);

    public void pipelineCommand(String key) {
        // 正确用法：Try-With-Resources 保证无论是否异常，资源和连接状态都会被管理
        // jedisPool会归还连接
        // pipeline 会发送DISCARD命令，将连接状态重置为正常模式
        try (Jedis jedis = jedisPool.getResource();
             Pipeline pipeline = jedis.pipelined()) { // Pipeline也会被自动关闭

            Response<String> r1 = pipeline.get(key);
            Response<Long> r2 = pipeline.incr(key);
            Response<Long> r3 = pipeline.expire(key,  60);
            // ... 更多命令

            pipeline.sync(); // 同步响应

            String value = r1.get();
            Long count = r2.get();
            Long expire = r3.get();
            LOGGER.info("pipelineCommand key={},value={},count={},expire={}", key, value, count, expire);
            // 无需手动调用 pipeline.close(), try-with-resources 会处理
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
// 此时，一个健康的连接已被返还到连接池
    }

    public void pipelineCommandError(String key) {
        // 错误用法：不主动归还常规模式的链接
        Jedis jedis = jedisPool.getResource();
        Pipeline pipeline = jedis.pipelined();

        Response<String> r1 = pipeline.get(key);
        Response<Long> r2 = pipeline.incr(key);
        Response<Long> r3 = pipeline.expire(key,  60);
        // ... 更多命令

        pipeline.sync(); // 同步响应

        String value = r1.get();
        Long count = r2.get();
        Long expire = r3.get();
        LOGGER.info("pipelineCommand key={},value={},count={},expire={}", key, value, count, expire);
    }

    public void normalCommand(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String r1 = jedis.get(key);
            Long r2 = jedis.incr(key);
            Long r3 = jedis.expire(key,  60);
            LOGGER.info("pipelineCommand key={},value={},count={},r3={}", key, r1, r2, r3);
        }
    }
}
