package com.example.demometrics.metrics;

import com.codahale.metrics.CachedGauge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年07月14日 17:31:00
 */
public class ActiveUsersGauge extends CachedGauge<List<Long>> {

    public ActiveUsersGauge(long timeout, TimeUnit timeoutUnit) {
        super(timeout, timeoutUnit);
    }

    private static final List<Long> result = new ArrayList<>();

    @Override
    public List<Long> loadValue() {
        return getActiveUserCount();
    }

    public List<Long> setActiveUserCount(Long remainTaskNum) {
        result.add(remainTaskNum);
        return result;
    }

    private List<Long> getActiveUserCount() {
        return result;
    }

}