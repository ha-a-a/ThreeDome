package com.example.demo;

import javassist.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Scanner;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2022/1/18 14:51
 */
public class MainDemo {

    public static void main(String[] args) {
        Hello firstHello = new Hello();
        firstHello.hello();
        // 输出pid
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        int index = name.indexOf("@");
        if (index != -1) {
            int pid = Integer.parseInt(name.substring(0, index));
            System.out.println("当前进程的PID为：" + pid);
        }
        // 产生中断等待
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        Hello hello2 = new Hello();
        hello2.hello();
        // 产生中断等待
        Scanner sc1 = new Scanner(System.in);
        sc1.nextInt();
        Hello hello3 = new Hello();
        hello3.hello();
        System.out.println("hello main end");
    }

//    private static void modifyClass() throws CannotCompileException, NotFoundException, IOException {
//        ClassPool classPool = new ClassPool(true);
//        classPool.insertClassPath("D:\\myspace\\ThreeDome\\demo-javaagent-hello\\target\\classes");
//        System.out.println("TransformerDemo 0000=" + classPool);
//        CtClass ctClass = classPool.get("com.example.demo.Hello");
//        System.out.println("TransformerDemo 11111=" + ctClass);
//        CtMethod method = ctClass.getDeclaredMethod("hello");
//        System.out.println("TransformerDemo 2222=" + method.getName());
//        String source = "{System.out.println(\"hello transformer!\");}";
//        method.setBody(source);
//        ctClass.writeFile("D:\\myspace\\ThreeDome\\demo-javaagent-hello\\target\\classes");
//        ctClass.detach();
//    }

}
