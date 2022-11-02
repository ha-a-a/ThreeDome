//package com.example.demo.config;
//
//import cn.hutool.core.lang.Snowflake;
//import io.shardingsphere.core.keygen.KeyGenerator;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @FUNC
// * @Author mengyuetang
// * @createTime 2020/4/5
// * @Desc
// */
//public class CustomSnowflakeKeGenerator implements KeyGenerator {
//
//    private Snowflake snowflake;
//
//    public CustomSnowflakeKeGenerator(Snowflake snowflake){
//        this.snowflake=snowflake;
//    }
//    @Override
//    public Number generateKey() {
//        return this.snowflake.nextId();
//    }
//}
