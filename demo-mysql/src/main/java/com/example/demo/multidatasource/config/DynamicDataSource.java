package com.example.demo.multidatasource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 默认不加注解使用operator数据源
     *
     * @return
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    private static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
