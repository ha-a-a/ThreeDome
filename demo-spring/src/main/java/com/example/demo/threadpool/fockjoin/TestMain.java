package com.example.demo.threadpool.fockjoin;

import java.util.stream.LongStream;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 17:46
 */
public class TestMain {

    public static void main(String[] args) {
        long[] nums = LongStream.rangeClosed(1, 1000).toArray();
        Calculator calculator = new ForkJoinCalculatorImpl();
        Long result = calculator.sumUp(nums);
        System.out.println("result=" + result+",corePool=");
    }
}
