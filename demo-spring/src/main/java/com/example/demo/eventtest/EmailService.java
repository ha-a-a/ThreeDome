package com.example.demo.eventtest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/2/7
 * @Desc
 */
@Slf4j
@Service
public class EmailService implements ApplicationListener {

    @Autowired
    private UserLogOffService userLogOffService;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        if (source instanceof UserInfo) {
            log.info("user was logged off. initial user's score and send message to user, user={}", JSONObject.toJSONString(source));
        } else if (source instanceof String) {
            String name = (String) source;
            log.info("user was registered. initial user's score and send message to user, name={}", name);
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setAge(18);
            userLogOffService.logOff(userInfo);
        } else {
            log.warn("unkown event");
        }
    }
}
