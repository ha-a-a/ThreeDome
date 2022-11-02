package demo.nacos.controller;

import com.alibaba.fastjson.JSONObject;
import demo.nacos.service.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/9/9
 * @Desc
 */
@RestController
@RequestMapping("/consumer")
public class NacosConsumerController {
    @Autowired
    private ConsumerServiceImpl consumerService;
    @GetMapping("/get")
    public String test(){
        return JSONObject.toJSONString(consumerService.sayName("tmyTry"));
    }
}
