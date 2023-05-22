package com.example.demo.groovy;

import groovy.lang.Script;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年05月22日 16:52:00
 */
abstract class MyScript extends Script {
    String name;

    String greet() {
        return "Hello, $name";
    }
}
