package com.example.demo.mode.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/17
 * @Desc
 */
@Slf4j
public abstract class AbstractTemplate {

    /**
     * 抽象方法共子类实现
     *
     * @param msg
     * @return
     */
    public abstract String realSay(String msg);

    /**
     * 调用抽象方法的方法
     *
     * @param msg
     * @return
     */
    public String saySomething(String msg) {
        log.info("Before realSay，I want to saySomeThing——{}", msg);
        String realSay = realSay(msg);
        log.info("After realSay, I known something ——{}, hahahaha",realSay);
        return realSay;
    }
}
