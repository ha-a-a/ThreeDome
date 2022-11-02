package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/4/4
 * @Desc
 */
@Service
public class UserRedisService {
    @Autowired
    StringRedisTemplate redisTemplate;
    public static final Logger LOGGER = LoggerFactory.getLogger(UserRedisService.class);

    public void redisBoundTest() {
        String key = "material-activityInfo:5ca56cdb6804540006248ecd";
        BoundHashOperations<String, String, Integer> boundHashOperations = redisTemplate.boundHashOps(key);
        Map<Object, Object> opsfor = redisTemplate.opsForHash().entries(key);
        Integer res = boundHashOperations.get("priority");
        LOGGER.info("res:{},opsfor:{}", res, opsfor);
    }
}
