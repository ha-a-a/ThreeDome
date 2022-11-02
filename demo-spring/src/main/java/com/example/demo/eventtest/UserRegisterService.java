package com.example.demo.eventtest;

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
public class UserRegisterService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void register(String name) {
        log.info("user register name event publish. name={}", name);
        applicationEventPublisher.publishEvent(new UserRegisterEvent(name));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
