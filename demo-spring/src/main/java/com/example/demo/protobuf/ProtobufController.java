package com.example.demo.protobuf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/8/27
 * @Desc
 */
@RestController
@RequestMapping("/protobuf")
@Slf4j
public class ProtobufController {
    @GetMapping("/helloword")
    public String test(@RequestParam String msg) {
        return msg;
    }

    @RequestMapping(value = "/test", produces = "application/x-protobuf",method = RequestMethod.GET)
    public MessageUser.MessageUserLogin testProto() {
        MessageUser.MessageUserLogin.Builder builder = MessageUser.MessageUserLogin.newBuilder();
        builder.setUsername("test-tmy");
        builder.setAccessToken(UUID.randomUUID().toString()+"res_");
        return builder.build();
    }
}
