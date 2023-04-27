package com.example.demo.mongo.pojo.autoinc;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
public interface SomeDocumentRepo extends MongoRepository<SomeDocument,String> {
}
