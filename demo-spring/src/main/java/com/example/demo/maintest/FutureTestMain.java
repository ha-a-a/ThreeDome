package com.example.demo.maintest;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/18
 * @Desc
 */
public class FutureTestMain {
    //test CompletableFuture
    public static void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            System.out.println("task doing");
            try {
                Thread.sleep(3000);
                //抛异常，使线程退出，future的get方法将阻塞，一直等待
                int none = 1 / 0;
            } catch (InterruptedException e) {
//                e.printStackTrace();
                //获取异常
                future.completeExceptionally(e);
            }
            future.complete("ok");
        }).start();
        //future的get方法是阻塞的
//        String res=future.get();
//        System.out.println("get future : "+res);
    }

    //netty 时间轮的实现，时间轮里面的任务是串行的，当前任务的延迟会影响后面任务开始执行的时间
    public static void testHashedWheelTimer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(500, TimeUnit.MILLISECONDS);
        System.out.println("start: " + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("task1: " + LocalDateTime.now().format(formatter));
        }, 3000, TimeUnit.MILLISECONDS);
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task2: " + LocalDateTime.now().format(formatter));
        }, 3000, TimeUnit.MILLISECONDS);
    }

    /**
     * 内存泄漏：没必要的存活的对象没有被GC回收，内存溢出：必须活着的对象超出了设置的最大内存Xmx
     * java堆内存泄露的积累导致内存溢出
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void testOOM() {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {

    }

    

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testCompletableFuture();
//        testHashedWheelTimer();
        testOOM();
    }
}
