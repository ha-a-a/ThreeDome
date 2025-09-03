package com.example.demo.mongo.pojo.bindingdata;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/14
 * @Desc
 */
@Data
@Document("timeline_material")
public class TimelineMaterialInfo extends MaterialInfo {

    public String activityUrl;
    public Integer showNum;
    public Integer interval;
}
