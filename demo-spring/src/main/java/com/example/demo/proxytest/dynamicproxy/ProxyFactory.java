package com.example.demo.proxytest.dynamicproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/5/28
 * @Desc
 */
public class ProxyFactory {

    private static Logger log = LoggerFactory.getLogger(ProxyFactory.class);
    private Object target;

    public ProxyFactory(Object o) {
        this.target = o;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (object, method, args) -> {
            // debug 模式下，idea会多次调用代理类实现目标类toString()的方法
            log.info("{} invoke begin",method.getName());
            Object invoke = method.invoke(target, args);
            log.info("{} invoke end",method.getName());
            return invoke;
        });
    }
}
