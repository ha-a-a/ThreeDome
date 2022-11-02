package com.example.demo.utils;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
@Component
public class OrderShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<String> shardingValue) {
        String targetTable = "tb_order_" + shardingValue.getValue();
        if (availableTargetNames.contains(targetTable)){
            return targetTable;
        }

        throw new UnsupportedOperationException("无法判定的值: " + shardingValue.getValue());
    }
}