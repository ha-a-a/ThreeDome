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
@Document("TeacherInfo")
@CompoundIndexes({
        @CompoundIndex(def = "{name:1}", unique = true)
})
@Data
@Builder
public class TeacherInfo {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Integer age;
    private Integer subjectId;
    private Integer studentCounts;
    // Spring data 提供的@version，所以应该结合jpa使用
    @Version
    private Integer version;
}
