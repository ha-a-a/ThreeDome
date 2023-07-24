package com.example.demometrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;
import com.example.demometrics.metrics.ActiveUsersGauge;
import com.example.demometrics.metrics.BaseMetric;
import com.example.demometrics.metrics.MetricEntity;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class DemoMetricsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testReport() throws InterruptedException {
        // 开启定时任务
        BaseMetric.start();
        // 采集Timer指标，分位数
        MetricEntity timerMetricEntity = MetricEntity.builder().metricName("op_user_request_qps")
                .metricLabel(MetricEntity.MetricLabel.builder()
                        .serviceName("demo-metric").host("120.0.0.1").method("testTimer").scope("all").build()).build();
        Timer timer = BaseMetric.timer(timerMetricEntity);

        // 采集Counter指标，单调递增
        MetricEntity counterMetricEntity = MetricEntity.builder().metricName("op_something_count")
                .metricLabel(MetricEntity.MetricLabel.builder()
                        .serviceName("demo-metric").host("120.0.0.1").method("testCounter").scope("all").build()).build();
        Counter counter = BaseMetric.counter(counterMetricEntity);

        // 采集Gauge指标, 比例，任务完成数，瞬时数据
        MetricEntity gaugeMetricEntity = MetricEntity.builder().metricName("op_user_task_count")
                .metricLabel(MetricEntity.MetricLabel.builder()
                        .serviceName("demo-metric").host("120.0.0.1").method("testGauge").scope("remain").build()).build();
        ActiveUsersGauge gauge = BaseMetric.gauge(gaugeMetricEntity);

        // 采集Meter指标,meter指标测量事件或请求的增长率,timer中基于meter计算
        MetricEntity meterMetricEnter = MetricEntity.builder().metricName("op_user_behavir_meter")
                .metricLabel(MetricEntity.MetricLabel.builder()
                        .serviceName("demo-metric").host("120.0.0.1").method("testMeter").scope("all").build()).build();
        Meter meter = BaseMetric.meter(meterMetricEnter);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    meter.mark();
                    gauge.setActiveUserCount((long) RandomUtils.nextInt(0, 1000));
                    counter.inc();
                    Timer.Context time = timer.time();
                    Thread.sleep(500);
                    time.stop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        Thread.sleep(5 * 60 * 1000);
    }
}
