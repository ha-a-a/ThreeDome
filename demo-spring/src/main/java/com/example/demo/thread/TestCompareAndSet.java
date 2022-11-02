package com.example.demo.thread;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/9/3
 * @Desc
 */
public class TestCompareAndSet {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                int expect = cas.getValue();
                System.out.println("expect=" + expect + ",cas" + cas.compareAndSet(expect, (int) Math.random() * 101));
            }).start();
        }

    }
}
