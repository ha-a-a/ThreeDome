//package com.example.demo.config;
//
//import cn.hutool.core.lang.Snowflake;
//import cn.hutool.core.util.IdUtil;
//import com.example.demo.utils.OrderShardingAlgorithm;
//import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
//import io.shardingsphere.api.config.rule.TableRuleConfiguration;
//import io.shardingsphere.core.yaml.sharding.YamlShardingRuleConfiguration;
//import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.inject.Qualifier;
//import javax.sql.DataSource;
//import javax.xml.crypto.Data;
//
///**
// * @FUNC
// * @Author mengyuetang
// * @createTime 2020/4/5
// * @Desc
// */
//@Configuration
//public class DataSourceShardingConfig {
//
//    public static final Snowflake snowflake= IdUtil.createSnowflake(1,1);
//    @Bean("dataSource")
//    public DataSource dataSource(){
//
//        ShardingRuleConfiguration shardingRuleConfiguration=new ShardingRuleConfiguration();
//        shardingRuleConfiguration.getBindingTableGroups().add("tb_oder");
//        shardingRuleConfiguration.getTableRuleConfigs().add(orderTableRule());
//       return ShardingDataSourceFactory.createDataSource()   ;
//    }
////    @Bean
////    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource")DataSource dataSource)
//
//    private TableRuleConfiguration orderTableRule(){
//        TableRuleConfiguration tableRuleConfiguration=new TableRuleConfiguration();
//        tableRuleConfiguration.setKeyGenerator(new CustomSnowflakeKeGenerator(snowflake));
//        tableRuleConfiguration.setActualDataNodes("tmy.tb_order_${0...1}");
//        return tableRuleConfiguration;
//    }
//}
