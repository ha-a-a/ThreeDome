package com.example.demo.mongo.pojo.autoinc;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mongo.pojo.autoinc.annotation.AutoIncKey;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
@Data
@Document("json_object")
public class SomeDocument implements Serializable {
    private static final long serialVersionUID = -7437023317035583362L;
    @AutoIncKey
    @Id
    @Field("_id")
    private long id=0l;
    @Field("json")
    private JSONObject json;
}
