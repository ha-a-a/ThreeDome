package com.example.demo.thread;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/9/3
 * @Desc
 */

class CompareAndSwap {

    private int value;

    public synchronized int getValue() {
        return this.value;
    }

    public synchronized int compareAndSwap(int expectVal, int newVal) {
        int oldVal = value;
        if (expectVal == oldVal) {
            this.value = newVal;
        }
        return oldVal;
    }

    public synchronized boolean compareAndSet(int expectVal, int newVal) {
        return expectVal == compareAndSwap(expectVal, newVal);
    }
}
