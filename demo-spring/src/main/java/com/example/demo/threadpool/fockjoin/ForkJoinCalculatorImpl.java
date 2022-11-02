package com.example.demo.threadpool.fockjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: 
 * @date: 2021/11/9 17:34
 */
public class ForkJoinCalculatorImpl implements Calculator {
    private ForkJoinPool pool;

    public ForkJoinCalculatorImpl() {
        pool = new ForkJoinPool();
        System.out.println("corePoll="+pool.getPoolSize()+",getParallelism="+pool.getParallelism()+pool.getQueuedTaskCount());
    }

    @Override
    public Long sumUp(long[] nums) {
        return pool.invoke(new SumTask(nums, 0, nums.length - 1));
    }


    private static class SumTask extends RecursiveTask<Long> {
        private long[] nums;
        private int from;
        private int to;

        private SumTask(long[] nums, int from, int to) {
            this.nums = nums;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += nums[i];
                }
                return total;
            } else {
                int mid = (to - from) / 2 + from;
                SumTask leftTask = new SumTask(nums, from, mid);
                SumTask rightTask = new SumTask(nums, mid + 1, to);
                leftTask.fork();
                rightTask.fork();
                return leftTask.join() + rightTask.join();
            }
        }
    }
}
