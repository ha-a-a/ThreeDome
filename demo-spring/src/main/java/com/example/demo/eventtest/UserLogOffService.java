package com.example.demo.eventtest;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @FUNC EventPublisher
 * @Author mengyuetang
 * @createTime 2021/2/7
 * @Desc
 */
@Slf4j
@Service
public class UserLogOffService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void logOff(UserInfo userInfo) {
        log.info("user logOff event publish. userInfo={}", JSONObject.toJSONString(userInfo));
        applicationEventPublisher.publishEvent(new UserLogOffEvent(userInfo));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
