package com.example.demo.mongo.web;

import com.example.timeline.demo.common.RestResult;
import com.example.timeline.demo.common.RestResultGenerator;
import com.example.demo.mongo.pojo.bindingdata.MaterialInfo;
import com.example.demo.mongo.pojo.json.JsonObject;
import com.example.demo.mongo.pojo.json.JsonObjectRepo;
import com.example.demo.mongo.service.MaterialInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
@RestController
@RequestMapping("/com/example/demo/mongo")
public class UserMongoController {
    public static final Logger LOGGER= LoggerFactory.getLogger(UserMongoController.class);
    @Autowired
    JsonObjectRepo jsonObjectRepo;
    @Autowired
    MaterialInfoService materialInfoService;
    @Autowired
    MongoTemplate mongoTemplate;
    @PostMapping("/testjson")
    public RestResult save(@RequestBody JsonObject jsonObject){
        LOGGER.info(jsonObject.getJson().toString());
        mongoTemplate.save(jsonObject);
        return RestResultGenerator.genSuccessResult();
    }
    @PostMapping("/abstractPojo")
    public RestResult save(@Valid @RequestBody MaterialInfo material) throws Exception {
        LOGGER.info("material:{}",material);
        materialInfoService.save(material);
        mongoTemplate.save(material);
        return RestResultGenerator.genSuccessResult(material);
    }
}
