package com.example.demo.intercepter.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 基于动态代理实现，有两种拦截器：HandlerInterceptor和MethodInterceptor
 * HandlerInterceptor是mvc项目中的拦截器，在DispatcherServlet中的doDispatch中调用interceptor的代理对象，和filter达到的目的一致
 * MethodInterceptor的实现分为MethodInterceptor接口和AspectJ注解
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 20:38
 */
@Component
@Slf4j
public class CustomMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        log.info("method interceptor begin, method name={}", method.getName());
        return methodInvocation.proceed();
    }
}
