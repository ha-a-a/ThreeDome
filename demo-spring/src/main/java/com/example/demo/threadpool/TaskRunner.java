package com.example.demo.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/12/26
 * @Desc
 */
public class TaskRunner implements Runnable, Comparable<TaskRunner> {
    public static final Logger log = LoggerFactory.getLogger(TaskRunner.class);

    private Integer order;

    public TaskRunner(int order) {
        this.order = order;
    }

    @Override
    public void run() {
        log.info("task {} run .... ", order);
        for (int i = 0; i < 10000;) {
            i++;
        }
    }

    @Override
    public int compareTo(TaskRunner o) {
        return this.order.compareTo(o.order);
    }
}
