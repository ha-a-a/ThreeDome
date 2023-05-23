package com.example.demo.groovy;

import groovy.lang.Script;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年05月22日 16:52:00
 */
public abstract class MyScript extends Script {
    private String name;
    private String replyName;

    public String greet() {
        System.out.println(name);
        return "hello, it's " + name;

    }

    public String reply() {
        System.out.println(replyName);
        return "hello, i'm " + replyName;

    }
}
