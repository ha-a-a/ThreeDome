package com.example.demoneo4j.domain.movie.model;

import com.example.demoneo4j.domain.person.model.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

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
@Node("Movie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    private  String title;
    @Property("tagline")
    private  String description;
    /**
     * 两种写法导致movie 被save时，ACTED_IN 关系每次都会创建，DIRECTED 关系会被更新，是因为ACTED_IN的关系存在属性，为了保证属性的唯一，主键必须自动生成的，
     * DIRECTED没有关系属性，所以不需要唯一id
     */
    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private Set<ActRelationEntity> actors = new HashSet<>();
    @Relationship(type = "DIRECTED", direction = INCOMING)
    private Set<PersonEntity> directors = new HashSet<>();
    //Getters omitted for brevity
}
