package com.example.demo.multidatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@Configuration
@Slf4j
public class AppConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.operator")
    public DataSource operatorDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.message")
    public DataSource messageDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource operatorDataSource,DataSource messageDataSource){
        Map<Object,Object> targetDataSource=new HashMap<>(2);
        targetDataSource.put(DataSourceNames.OPERATOR,operatorDataSource);
        targetDataSource.put(DataSourceNames.MESSAGE,messageDataSource);
        log.info("targetDataSource: {}", targetDataSource);
        return new DynamicDataSource(operatorDataSource,targetDataSource);
    }
}
