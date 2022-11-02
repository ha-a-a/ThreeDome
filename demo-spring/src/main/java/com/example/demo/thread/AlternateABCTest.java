package com.example.demo.thread;

public class AlternateABCTest {
    public static void main(String[] args) {
        Alternate alternate = new Alternate();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternate.printA(i);
            }
        }, "线程A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternate.printB(i);
            }
        }, "线程B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternate.printC(i);
            }
        }, "线程C").start();
    }
}
