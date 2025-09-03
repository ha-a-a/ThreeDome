package com.example.demometrics.metrics;

import lombok.Builder;
import lombok.Data;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年07月14日 14:36:00
 */
@Data
@Builder
public class MetricEntity {
    private String metricName;
    private MetricLabel metricLabel;

    public String getUniqueName() {
        return new StringBuffer().append(metricName)
                .append(metricLabel.serviceName)
                .append(metricLabel.host)
                .append(metricLabel.scope)
                .append(metricLabel.method)
                .toString();
    }

    @Data
    @Builder
    public static class MetricLabel {
        private String serviceName;
        private String host;
        private String method;
        private String scope;
    }
}
