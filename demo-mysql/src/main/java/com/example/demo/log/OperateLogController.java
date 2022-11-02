package com.example.demo.log;

import com.example.demo.log.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {
    @Autowired
    OperateLogService operateLogService;

        @OperateLog(module = OpModuleType.LOGIN,webName = OpWebType.HOME,desc = "测试测试")
    @PostMapping("/login")
    public String login(@RequestBody UserVo userVo, HttpServletRequest request) throws Exception {
        return String.format("%s login success by passport= %s",
                userVo.getName(), userVo.getPassport());
    }
}
