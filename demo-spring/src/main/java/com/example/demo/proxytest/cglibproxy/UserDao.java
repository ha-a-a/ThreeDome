package com.example.demo.proxytest.cglibproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @FUNC 目标类
 * @Author mengyuetang
 * @createTime 2021/5/28
 * @Desc
 */
@Slf4j
public class UserDao {
    public void save() {
        log.info("cglib代理目标类save");
    }

    public final void update() {
        log.info("cglib代理目标类update");
    }

    public void delete() {
        log.info("cglib代理目标类update");
    }
}
