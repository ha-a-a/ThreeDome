package com.example.demo.proxytest.cglibproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {
    private static Logger log = LoggerFactory.getLogger(CglibProxyFactory.class);

    // 目标对象
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象创建代理对象（目标类的子类）
    public Object getProxyInstance() {
        // 工具类
        Enhancer en = new Enhancer();
        // 设置代理对象的父类，也即目标对象
        en.setSuperclass(target.getClass());
        // 设置回调
        en.setCallback(this);
        // 创建代理对象（目标类的子类）
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("{} cglib invoke before", method.getName());
        method.invoke(target, args);
        log.info("{} cglib invoke after", method.getName());
        return null;
    }
}
