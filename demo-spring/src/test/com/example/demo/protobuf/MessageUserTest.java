package com.example.demo.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/8/27
 * @Desc
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MessageUserTest {
    @Test
    public void testProtobuf() throws InvalidProtocolBufferException {
        MessageUser.MessageUserLogin messageUserLogin = MessageUser.MessageUserLogin.newBuilder()
                .setAccessToken("test_token")
                .setUsername("tmybb")
                .build();
        log.info("messageUserLogin.toString() --- {}", messageUserLogin.toString());
        log.info("messageUserLogin.toByteArray() --- {}", messageUserLogin.toByteArray());
        log.info("messageUserLogin.toByteString() --- {}", messageUserLogin.toByteString());
        MessageUser.MessageUserLogin messageUserLogin1 = messageUserLogin.getParserForType().parseFrom(messageUserLogin.toByteArray());
        log.info("messageUserLogin.parseFrom() --- {}", messageUserLogin1);
        log.info("messageUserLogin.toString() --- {}", messageUserLogin1.toString());

    }

}