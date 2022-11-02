package com.example.demo.eventtest;

import org.springframework.context.ApplicationEvent;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/2/7
 * @Desc
 */
public class UserRegisterEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(Object source) {
        super(source);
    }
}
