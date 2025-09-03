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
@Document(value = "list_material")
public class ListMaterialInfo extends MaterialInfo {

    public String activityUrl;

    public String note;
}
