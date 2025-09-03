package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author tangmengyue
 * @version 1.0.0
 * @ClassName RedisConfig.java
 * @Description TODO
 * @createTime 2025-09-02 18:21:00
 */
@Configuration
@EnableConfigurationProperties
@Slf4j
class RedisConfig {
    @Autowired
    RedisProperties redisProperties;

    /**
     * 配置Jedis连接池
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(20);          // 最大连接数
        poolConfig.setMaxIdle(10);           // 最大空闲连接数
        poolConfig.setMinIdle(2);            // 最小空闲连接数
        poolConfig.setMaxWaitMillis(1000);   // 获取连接时的最大等待时间(毫秒)
        poolConfig.setTestOnBorrow(true);    // 在获取连接时检查连接有效性
        poolConfig.setTestOnReturn(true);    // 在归还连接时检查连接有效性
        poolConfig.setTestWhileIdle(true);   // 在空闲时检查连接有效性
        poolConfig.setTimeBetweenEvictionRunsMillis(30000); // 空闲连接检查周期(毫秒)
        return poolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
        // 创建 JedisPool
        JedisPool jedisPool;
        jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout().getNano(), redisProperties.getPassword());
        log.info("JedisPool initialized successfully. Host: {}, Port: {}", redisProperties.getHost(), redisProperties.getPort());
        return jedisPool;
    }


    /**
     * 配置Jedis连接工厂
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisProperties.getHost());
        factory.setPort(redisProperties.getPort());
        factory.setPassword(redisProperties.getPassword()); // 如果有密码则设置
        factory.setDatabase(redisProperties.getDatabase());
        factory.setTimeout(2000); // 连接超时时间(毫秒)
        factory.setUsePool(true); // 使用连接池
        factory.setPoolConfig(jedisPoolConfig); // 注入连接池配置
        return factory;
    }

    /**
     * 配置RedisTemplate，并设置序列化器
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // 使用GenericJackson2JsonRedisSerializer来序列化和反序列化redis的value值
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}
