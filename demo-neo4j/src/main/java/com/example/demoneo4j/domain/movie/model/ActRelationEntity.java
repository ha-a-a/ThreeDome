package com.example.demoneo4j.domain.movie.model;

import com.example.demoneo4j.domain.person.model.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

/**
 * @author tangmengyue
 * @ClassName MovieEntity.java
 * @Description TODO
 * @createTime 2023年01月30日 09:59:00
 */
@Data
@RelationshipProperties
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActRelationEntity {
    //为了保护属性的唯一性，必须内部生成id
    @RelationshipId
    private Long id;
    private String role;
    @TargetNode
    private PersonEntity person;

    //Getters omitted for brevity
}
