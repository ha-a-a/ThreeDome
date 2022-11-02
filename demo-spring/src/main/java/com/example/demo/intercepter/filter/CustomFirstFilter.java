package com.example.demo.intercepter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: 基于servlet的doFiler回调实现
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 19:12
 */
@Slf4j
@Component
public class CustomFirstFilter implements Filter {

    private String[] noFilters;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("first filter init");
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
        log.info("first doFilter before, path={}", request.getRequestURL().toString());
        String requestURI = request.getRequestURI();
        boolean flag = isFilter(requestURI);
        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("first request forward /noRoot");
            request.getRequestDispatcher("/noRoot").forward(servletRequest, servletResponse);
        }
        stopWatch.stop();
        log.info("first doFilter after, path={},cost={}", request.getRequestURL().toString(), stopWatch.getTaskInfo());

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
        log.info("first filter destroy");
    }
}
