package com.example.demo.redisson;

import com.alibaba.fastjson.JSONObject;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/5/6
 * @Desc
 */
@Service
public class RedissonService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RedissonService.class);
    @Autowired
    RedissonClient redissonClient;

    public void testLock() {
        new Thread(() -> {
            // 加分布式锁
            LOGGER.info("thread1 begin");
            String lock = "redissonTest";
            RLock rLock = redissonClient.getLock(lock);
            boolean res;
            try {
                res = rLock.tryLock(100, 10, TimeUnit.SECONDS);
                LOGGER.info("res1:{}", res);
            } catch (InterruptedException e) {
                LOGGER.error("update lock failed,key:", lock);
                return;
            }
            if (res) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    LOGGER.error("thread sleep failed,key:", lock);
                } finally {
                    rLock.unlock();
                }
            }
            LOGGER.info("thread1 end");
        }).start();
        new Thread(() -> {
            // 加分布式锁
            LOGGER.info("thread2 begin");
            String lock = "redissonTest";
            RLock rLock = redissonClient.getLock(lock);
            boolean res;
            try {
                res = rLock.tryLock(100, 10, TimeUnit.SECONDS);
                LOGGER.info("res2:{}", res);

            } catch (InterruptedException e) {
                LOGGER.error("update lock failed,key:", lock);
                return;
            }
            if (res) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    LOGGER.error("thread sleep failed,key:", lock);
                } finally {
                    rLock.unlock();
                }
            }
            LOGGER.info("thread2 end");
        }).start();
    }

    public JSONObject testBloomFilter(Integer capacity, Float rate, String filterName, String value, String value2) {
        RBloomFilter<String> firstBloomFilter = redissonClient.getBloomFilter(filterName);
        boolean b = firstBloomFilter.tryInit(capacity, rate);
        LOGGER.info("filter has exists. {}", b);
        for (int i = 0; i < capacity / 10; i++) {
            boolean added = firstBloomFilter.add(value + i);
            LOGGER.info("{} add item={},isSuc={}", filterName, (value + i), added);
        }
        long count = firstBloomFilter.count();
        long errorCount = 0;
        for (int i = 0; i < capacity / 10; i++) {
            boolean contains = firstBloomFilter.contains(value2 + i);
            if (!contains) {
                // 错误率
                errorCount++;
            }
            LOGGER.info("{} in {} is {}", (value2 + i), filterName, contains);
        }
        LOGGER.info("{} count={}, errorCount={}", filterName, count, errorCount);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("errorCount", errorCount);
        return jsonObject;
    }


}
