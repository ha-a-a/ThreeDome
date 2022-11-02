package com.example.demo;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2022/1/18 15:18
 */
public class AgentDemo {
    /**
     * jvm启动后加载agentmain
     *
     * @param args
     * @param inst
     */
    public static void agentmain(String args, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("hello, agentmain agent exec after main, args=" + args);
        String[] split = args.split(",");
        String modifiedClassName = split[0];
        String modifiedClassMethod = split[1];
        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class loadedClass : allLoadedClasses) {
            if (loadedClass.getName().equals(modifiedClassName)) {
                System.out.println("agentmain agent: load class=" + loadedClass.getName() + ",modifiable=" + inst.isModifiableClass(loadedClass));
                // 只会动态修改字节码，不会修改class文件
                inst.addTransformer(new TransformerDemo(modifiedClassName, modifiedClassMethod), true);
                inst.retransformClasses(loadedClass);
            }
        }
    }

}

