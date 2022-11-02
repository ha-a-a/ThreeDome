package com.example.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alternate {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(int totalLoop) {
        lock.lock();
        try {
            if (number != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": i=" + i + "totalLoop=" + totalLoop);
            }
            number = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public void printB(int totalLoop) {
        lock.lock();
        try {
            if (number != 2) {
                conditionB.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": i=" + i + "totalLoop=" + totalLoop);
            }
            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public void printC(int totalLoop) {
        lock.lock();
        try {
            if (number != 3) {
                conditionC.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": i=" + i + "totalLoop=" + totalLoop);
            }
            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }
}
