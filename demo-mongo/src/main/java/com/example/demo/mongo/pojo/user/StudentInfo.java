package com.example.demo.mongo.pojo.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author tangmengyue
 * @ClassName StudentInfo.java
 * @Description TODO
 * @createTime 2023年04月26日 11:10:00
 */
@Document
@Data
@Builder
@CompoundIndexes({
        @CompoundIndex(def = "{name:1}", unique = true)
})
public class StudentInfo {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String teacherId;
    private Integer ranking;
    @Version
    private Integer version;

}
