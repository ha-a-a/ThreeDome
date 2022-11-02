package com.example.demo.graceoffline;

import org.omg.SendingContext.RunTime;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/19 19:30
 */
public class GracefulOffline {
    /**
     * 系统进程正常关闭(kill -15,)和强制关闭(kill -9)
     */
    /**
     * jvm优雅下线：正常关闭 kill -15 pid , 强制关闭 kill -9 pid, 异常关闭，Runtime
     * jvm会提shutdown hook
     */
    public static void main(String[] args) {
        boolean flag = true;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("hook execute...");
        }));
        while (flag) {

        }
        System.out.println("main thread execute end....");
    }
    /**
     * spring优雅下线：监听ContextClosedEvent事件，进行bean的销毁，容器的销毁处理
     */

    /**
     * dubbo优雅下线：
     */
}
