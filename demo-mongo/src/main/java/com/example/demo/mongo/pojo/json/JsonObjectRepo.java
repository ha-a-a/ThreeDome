package com.example.demo.mongo.pojo.json;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
public interface JsonObjectRepo extends MongoRepository<JsonObject,String> {
}
