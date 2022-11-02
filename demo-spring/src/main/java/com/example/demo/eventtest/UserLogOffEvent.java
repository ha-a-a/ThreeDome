package com.example.demo.eventtest;

import org.springframework.context.ApplicationEvent;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/2/7
 * @Desc
 */
public class UserLogOffEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserLogOffEvent(UserInfo source) {
        super(source);
    }
}
