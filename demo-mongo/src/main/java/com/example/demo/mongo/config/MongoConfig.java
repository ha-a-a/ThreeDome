package com.example.demo.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @author tangmengyue
 * @ClassName DBTranslactionConfig.java
 * @Description TODO
 * @createTime 2023年04月26日 10:53:00
 */
@Configuration
public class MongoConfig   {
    @Bean
    MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
