package com.example.demo.mongo.web;

import com.example.demo.mongo.pojo.autoinc.SomeDocument;
import com.example.demo.mongo.pojo.autoinc.SomeDocumentRepo;
import com.example.demo.mongo.pojo.bindingdata.MaterialInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
@RestController
@RequestMapping("/com/example/demo/mongo")
public class UserMongoController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserMongoController.class);
    @Autowired
    SomeDocumentRepo someDocumentRepo;
    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/testjson")
    public SomeDocument save(@RequestBody SomeDocument someDocument) {
        LOGGER.info(someDocument.getJson().toString());
        return mongoTemplate.save(someDocument);
    }

    @PostMapping("/abstractPojo")
    public MaterialInfo save(@RequestBody MaterialInfo material) throws Exception {
        LOGGER.info("material:{}", material);
        return mongoTemplate.save(material);
    }
}
