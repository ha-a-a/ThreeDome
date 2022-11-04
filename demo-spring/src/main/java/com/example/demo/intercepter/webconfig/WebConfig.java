package com.example.demo.intercepter.webconfig;

import com.example.demo.intercepter.filter.CustomFirstFilter;
import com.example.demo.intercepter.filter.CustomSecondFilter;
import com.example.demo.intercepter.interceptor.CustomMethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 19:29
 */
@Configuration
public class WebConfig {
    @Autowired
    private CustomFirstFilter firstFilter;
    @Autowired
    private CustomSecondFilter secondFilter;
    @Autowired
    private CustomMethodInterceptor logInterceptor;

    @Bean
    public FilterRegistrationBean<CustomFirstFilter> filterRegistrationBean() {
        FilterRegistrationBean<CustomFirstFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 过滤路径
        filterRegistrationBean.addUrlPatterns("/filter/*","/interceptor/*");
        // nofilter，不过滤路径
        filterRegistrationBean.addInitParameter("noFilter", "/filter/one,/filter/two,/filter/index,/filter/noRoot,/interceptor/five");
        filterRegistrationBean.setName("CustomFirstFilter");
        filterRegistrationBean.setFilter(firstFilter);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomSecondFilter> secondFilterRegistrationBean() {
        FilterRegistrationBean<CustomSecondFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 过滤路径
        filterRegistrationBean.addUrlPatterns("/filter/*","/interceptor/*");
        filterRegistrationBean.addInitParameter("noFilter", "/filter/four,/filter/three,/filter/index,/filter/noRoot,/interceptor/five");
        filterRegistrationBean.setName("CustomSecondFilter");
        filterRegistrationBean.setFilter(secondFilter);
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Autowired
    private CustomMethodInterceptor customMethodInterceptor;

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(com.example.demo.intercepter.interceptor.CustomMethodAnnotation)");
        DefaultPointcutAdvisor advice = new DefaultPointcutAdvisor();
        advice.setPointcut(pointcut);
        advice.setAdvice(customMethodInterceptor);
        return advice;
    }

}
