package com.example.demo;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2022/1/18 14:51
 */
public class PreDemo {
    /**
     * jvm启动前加载，build时会编辑进目标对象的jar包中，通过 java -jar -javaagent:demo-javaagent.jar demo-javaagent-hello.jar
     *
     * @param args
     * @param inst 加载优先级更高
     */
    public static void premain(String args, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("hello, premain agent exec before main");
        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class loadedClass : allLoadedClasses) {
            System.out.println("premain agent: load class=" + loadedClass.getName() + ",modifiable=" + inst.isModifiableClass(loadedClass));
        }
    }
}
