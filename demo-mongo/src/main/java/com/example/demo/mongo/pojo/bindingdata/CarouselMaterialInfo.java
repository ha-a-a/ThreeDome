package com.example.demo.mongo.pojo.bindingdata;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/14
 * @Desc
 */
@Data
@Document("carousel_material")
public class CarouselMaterialInfo extends MaterialInfo {

    @Id
    @Field("_id")
    private String id;
    public String activityUrl;
}
