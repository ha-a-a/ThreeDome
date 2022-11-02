package com.example.demo.thread.entity;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/9/6 17:36
 */
public class Clerk {

    // 数据共享存在，安全问题
    private int count;

    // 进货
    public synchronized void getCount() {
        while (count >= 1) {
            System.out.println("库存已满，无法添加");
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("线程中断，e=" + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + "店员进货1个产品，库存为" + ++count);
        this.notifyAll();

    }

    // 卖货
    public synchronized void sale() {
        while (count <= 0) {
            System.out.println("库存紧缺，无法销售");
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("线程中断，e=" + e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + "店员销售1个产品，库存为" + --count);
        this.notifyAll();

    }
}
