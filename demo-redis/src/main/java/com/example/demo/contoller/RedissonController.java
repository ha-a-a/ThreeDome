package com.example.demo.contoller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.redisson.RedissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/5/6
 * @Desc
 */
@RestController
@RequestMapping("redisson/test")
public class RedissonController {
    @Autowired
    RedissonService redissonService;

    @GetMapping("/api/get")
    public void testLock() {
        redissonService.testLock();
    }

    @GetMapping("/api/bloomFilter")
    public JSONObject testBloomFilter(Integer capacity, Float rate,String filterName,String value,String value2) {
        JSONObject jsonObject = redissonService.testBloomFilter(capacity, rate, filterName, value,value2);
        return jsonObject;
    }
}
