package com.example.demo.mode.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/17
 * @Desc
 */
@Slf4j
public class AbstractTemplateImpl extends AbstractTemplate {
    @Override
    public String realSay(String msg) {
        log.info("real Say And do something");
        return "\'"+msg+"\'"+"/\n what it is above is really truth, xixixi";
    }

    public static void main(String[] args) {
        AbstractTemplate abstractTemplate = new AbstractTemplateImpl();
        String s = abstractTemplate.saySomething("wahahaha,it's me, the demo is so easy");
    }
}
