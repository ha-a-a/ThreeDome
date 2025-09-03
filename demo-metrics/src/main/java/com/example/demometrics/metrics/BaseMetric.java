package com.example.demometrics.metrics;

import com.codahale.metrics.*;
import com.example.demometrics.reporter.MetricsReporter;
import com.example.demometrics.sender.MetricSender;

import java.util.concurrent.TimeUnit;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年07月14日 14:05:00
 */
public class BaseMetric {
    private final static MetricRegistry metricRegistry = new MetricRegistry();
    private static final ScheduledReporter SCHEDULED_REPORTER =
            MetricsReporter.getDefaultInstance(metricRegistry, new MetricSender());

    public static void start() {
        SCHEDULED_REPORTER.start(30, TimeUnit.SECONDS);
    }

    public static Meter meter(MetricEntity entity) {
        return metricRegistry.meter(entity.getUniqueName());
    }

    public static Counter counter(MetricEntity entity) {
        return metricRegistry.counter(entity.getUniqueName());
    }

    public static ActiveUsersGauge gauge(MetricEntity entity) {
        return metricRegistry.register(entity.getUniqueName(), new ActiveUsersGauge(1, TimeUnit.MINUTES));
    }

    public static Timer timer(MetricEntity entity) {
        return metricRegistry.timer(entity.getUniqueName(), () -> new Timer(new SlidingTimeWindowArrayReservoir(1, TimeUnit.MINUTES)));
    }

}
