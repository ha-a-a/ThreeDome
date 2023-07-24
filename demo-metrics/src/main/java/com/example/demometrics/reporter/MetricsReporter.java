package com.example.demometrics.reporter;

import com.codahale.metrics.*;
import com.example.demometrics.sender.MetricSender;
import com.example.demometrics.sender.Remote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年07月14日 14:07:00
 */
public class MetricsReporter extends ScheduledReporter {
    private Logger log = LoggerFactory.getLogger(MetricsReporter.class);

    private static final String DEFAULT_REPORTER_NAME = "op-scheduled-reporter";

    private final MetricSender metricSender;
    private Clock clock;

    protected MetricsReporter(MetricRegistry registry, String name, MetricSender metricSender) {
        super(registry, name, MetricFilter.ALL, TimeUnit.SECONDS, TimeUnit.MILLISECONDS);
        this.metricSender = metricSender;
        this.clock = Clock.defaultClock();
    }

    public static ScheduledReporter getDefaultInstance(MetricRegistry metricRegistry, MetricSender metricSender) {
        return new MetricsReporter(metricRegistry, DEFAULT_REPORTER_NAME, metricSender);
    }

    private volatile boolean isStarted;


    @Override
    public void start(long period, TimeUnit unit) {
        if (isStarted) {
            return;
        }
        super.start(period, unit);
        this.isStarted = true;
    }

    @Override
    public void report(SortedMap<String, Gauge> gauges,
                       SortedMap<String, Counter> counters,
                       SortedMap<String, Histogram> histograms,
                       SortedMap<String, Meter> meters,
                       SortedMap<String, Timer> timers) {
        long time = clock.getTime();
        log.info("report time={}", time);
        for (Map.Entry<String, Gauge> stringGaugeEntry : gauges.entrySet()) {
            Gauge<List<Long>> gauge = stringGaugeEntry.getValue();
            List<Long> tasks = gauge.getValue();
            log.info("report gauges={}-{}", stringGaugeEntry.getKey(), tasks.size());
        }
        for (Map.Entry<String, Counter> stringCounterEntry : counters.entrySet()) {
            log.info("report counters={}-{}", stringCounterEntry.getKey(), stringCounterEntry.getValue().getCount());
        }
        for (Map.Entry<String, Meter> stringMeterEntry : meters.entrySet()) {
            Meter meter = stringMeterEntry.getValue();
            log.info("report meters={}-{}-{}-{}-{}-{}", stringMeterEntry.getKey(), meter.getCount(), meter.getMeanRate(), meter.getOneMinuteRate(), meter.getFiveMinuteRate(), meter.getFifteenMinuteRate());
        }
        for (Map.Entry<String, Timer> stringTimerEntry : timers.entrySet()) {
            Timer timer = stringTimerEntry.getValue();
            log.info("report meters={}-{}-{}-{}-{}-{}", stringTimerEntry.getKey(), timer.getCount(), timer.getMeanRate(), timer.getOneMinuteRate(), timer.getFiveMinuteRate(), timer.getFifteenMinuteRate());
        }
        log.info("report end!!!");

//        metricSender.send(buildRequestData(gauges, counters, histograms, meters, timers));
    }

    public Remote.WriteRequest buildRequestData(SortedMap<String, Gauge> gauges,
                                                SortedMap<String, Counter> counters,
                                                SortedMap<String, Histogram> histograms,
                                                SortedMap<String, Meter> meters,
                                                SortedMap<String, Timer> timers) {
        return null;
    }
}
