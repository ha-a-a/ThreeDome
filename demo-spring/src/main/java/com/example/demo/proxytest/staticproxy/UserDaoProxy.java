package com.example.demo.proxytest.staticproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @FUNC 静态代理类，由于代理类和目标类实现一样的接口，所以会产生过多的代理类
 * @Author mengyuetang
 * @createTime 2021/5/28
 * @Desc
 */
public class UserDaoProxy implements IUserDao {
    private static Logger log = LoggerFactory.getLogger(UserDaoProxy.class);

    private IUserDao userDao;

    public UserDaoProxy(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        log.info("代理类save");
        userDao.save();
    }
}
