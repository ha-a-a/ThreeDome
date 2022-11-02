package com.example.demo.eventtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/2/7
 * @Desc
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRegisterServiceTest {

    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private UserLogOffService userLogOffService;

    @Test
    public void testUserRegister() {
        userRegisterService.register("tmy");
    }

    @Test
    public void testUserLogOff() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(18);
        userInfo.setName("ttt");
        userLogOffService.logOff(userInfo);
    }
}