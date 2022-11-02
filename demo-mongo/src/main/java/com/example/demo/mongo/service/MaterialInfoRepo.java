package com.example.demo.mongo.service;

import com.example.timeline.demo.mongo.pojo.bindingdata.MaterialInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/15
 * @Desc
 */
@Repository
public interface MaterialInfoRepo extends MongoRepository<MaterialInfo,String> {
}
