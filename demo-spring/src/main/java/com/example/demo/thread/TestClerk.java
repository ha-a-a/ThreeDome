package com.example.demo.thread;

import com.example.demo.thread.entity.Clerk;

/**
 * @description: 测试wait的虚假唤醒
 * @author: mengyuetang
 * @email: 
 * @date: 2021/9/6 17:44
 */
public class TestClerk {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        // 一个生产者生产20次
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                clerk.getCount();
            }
        }, "生产者A").start();
        // 一个消费者消费20次
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                clerk.sale();
            }
        }, "消费者B").start();
        // 一个生产者生产20次
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                clerk.getCount();
            }
        }, "生产者C").start();
        // 一个消费者消费20次
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                clerk.sale();
            }
        }, "消费者D").start();
    }
}
