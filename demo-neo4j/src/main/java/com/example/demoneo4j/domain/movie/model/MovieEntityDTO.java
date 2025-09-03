package com.example.demoneo4j.domain.movie.model;

import com.example.demoneo4j.domain.person.model.PersonEntity;
import com.example.demoneo4j.domain.person.model.PersonEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

/**
 * @author tangmengyue
 * @ClassName MovieEntity.java
 * @Description TODO
 * @createTime 2023年01月30日 09:59:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntityDTO {
    private String title;
    private List<PersonEntityDTO> directors;
    //Getters omitted for brevity
}
