package com.example.demo.multidatasource.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@Component
@Aspect
@Slf4j
@Order(1)
public class DataSourceAspect {

    /**
     * 命名切点
     */
    @Pointcut("@within(DataSourceAnnotation)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object aroundByNamed(ProceedingJoinPoint pointcut) throws Throwable {
        log.info("命名切点");
        MethodSignature signature = (MethodSignature) pointcut.getSignature();
        Method method = signature.getMethod();
        // 获取方法上的@DataSource注解
        DataSourceAnnotation annotation = method.getAnnotation(DataSourceAnnotation.class);
        if (null == annotation) {
            // 获取类上的@DataSource注解
            annotation = pointcut.getTarget().getClass().getAnnotation(DataSourceAnnotation.class);
        }
        // 通过注解annotation的值设置是哪个数据源
        DynamicDataSource.setDataSource(annotation.value());
        log.info("set dataSource success. {}",annotation.value());
        // 执行被注解annotation注释的方法/类
        try {
           return pointcut.proceed();
        } finally {
            log.info("dataSource clear!");
            // 清除数据源
            DynamicDataSource.clearDataSource();
        }
    }

    /**
     * 匿名切点，同时存在命名切点，先走匿名切点，再走名名切点
     */
//    @Around("@within(DataSourceAnnotation)")
//    public Object aroundByAnonymous(ProceedingJoinPoint pointcut) throws Throwable {
//        log.info("匿名切点");
//        MethodSignature signature = (MethodSignature) pointcut.getSignature();
//        Method method = signature.getMethod();
//        // 获取方法上的@DataSource注解
//        DataSourceAnnotation annotation = method.getAnnotation(DataSourceAnnotation.class);
//        if (null == annotation) {
//            // 获取类上的@DataSource注解
//            annotation = pointcut.getTarget().getClass().getAnnotation(DataSourceAnnotation.class);
//        }
//        // 通过注解annotation的值设置是哪个数据源
//        DynamicDataSource.setDataSource(annotation.value());
//        log.info("set dataSource success. {}",annotation.value());
//        // 执行被注解annotation注释的方法/类
//        try {
//            return pointcut.proceed();
//        } finally {
//            log.info("dataSource clear!");
//            // 清除数据源
//            DynamicDataSource.clearDataSource();
//        }
//    }
}
