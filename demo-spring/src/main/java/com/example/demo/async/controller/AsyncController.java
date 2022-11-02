package com.example.demo.async.controller;

import com.example.demo.async.service.AsyncService;
import com.example.demo.common.RestResult;
import com.example.demo.common.RestResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/19
 * @Desc
 */
@RestController
@RequestMapping("/com/example/demo/async")
public class AsyncController {
    public static final Logger LOGGER= LoggerFactory.getLogger(AsyncController.class);
    @Autowired
    AsyncService asyncService;
    @PostMapping("/test")
    public RestResult<String> testAsync() throws InterruptedException {
        asyncService.testAsync();
        LOGGER.info("test com.example.demo.async finish!!!");
        return RestResultGenerator.genSuccessResult();
    }
}
