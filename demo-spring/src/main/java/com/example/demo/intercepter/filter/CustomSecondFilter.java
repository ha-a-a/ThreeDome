package com.example.demo.intercepter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 19:12
 */
@Slf4j
@Component
public class CustomSecondFilter implements Filter {
    /**
     * 无法注入bean，因为filter的生命周期有servlet管理而不是spring
     */
    @Autowired
    UserService userService;
    private String[] noFilters;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("second filter init,userService={}", userService);
        String noFilter = filterConfig.getInitParameter("noFilter");
        if (StringUtils.isEmpty(noFilter)) {
            return;
        }
        noFilters = noFilter.split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("second doFilter before, path={}", request.getRequestURL().toString());
        userService.justTest();
        String requestURI = request.getRequestURI();
        boolean flag = isFilter(requestURI);
        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("second request forward /index");
            request.getRequestDispatcher("/filter/index").forward(servletRequest, servletResponse);
        }
        stopWatch.stop();
        log.info("second doFilter after, path={},cost={}", request.getRequestURL().toString(), stopWatch.prettyPrint());

    }

    private boolean isFilter(String requestURI) {
        for (String noFilter : noFilters) {
            if (requestURI.contains(noFilter)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        log.info("second filter destroy");
    }
}
