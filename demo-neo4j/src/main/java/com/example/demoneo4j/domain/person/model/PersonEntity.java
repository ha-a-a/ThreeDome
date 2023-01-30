package com.example.demoneo4j.domain.person.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author tangmengyue
 * @ClassName PersonEntity.java
 * @Description TODO
 * @createTime 2023年01月30日 10:05:00
 */
@Node("Person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    private  String name;
    private  Integer born;
    public PersonEntity(Integer born, String name) {
        this.born = born;
        this.name = name;
    }
    //Getters omitted
}
