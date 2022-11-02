package com.example.demo.proxytest.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @FUNC 目标类
 * @Author mengyuetang
 * @createTime 2021/5/28
 * @Desc
 */
@Slf4j
public class UserDaoImpl implements IUserDao {
    @Override
    public void save() {
        log.info("目标类save");
    }
}
