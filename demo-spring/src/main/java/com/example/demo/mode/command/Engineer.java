package com.example.demo.mode.command;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/14
 * @Desc
 */

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Engineer implements Command {
    @Override
    public void execute() {
        log.info("Engineer execute");
    }
}
