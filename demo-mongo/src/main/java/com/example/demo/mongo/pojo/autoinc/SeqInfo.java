package com.example.demo.mongo.pojo.autoinc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */

@Data
@Document(collection = "sequence")
public class SeqInfo {

    @Id
    private String id;// 主键

    @Field
    private String collName;// 集合名称

    @Field
    private long seqId;// 序列值
}
