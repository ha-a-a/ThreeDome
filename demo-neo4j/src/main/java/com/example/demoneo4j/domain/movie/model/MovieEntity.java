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
    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private Set<PersonEntity> actors = new HashSet<>();
    @Relationship(type = "DIRECTED", direction = INCOMING)
    private Set<PersonEntity> directors = new HashSet<>();
    public MovieEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }
    //Getters omitted for brevity
}
