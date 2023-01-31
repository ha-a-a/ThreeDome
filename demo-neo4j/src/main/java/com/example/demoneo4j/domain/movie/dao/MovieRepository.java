package com.example.demoneo4j.domain.movie.dao;

import com.example.demoneo4j.domain.movie.model.MovieEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

/**
 * @author tangmengyue
 * @ClassName MovieRepository.java
 * @Description TODO
 * @createTime 2023年01月30日 10:42:00
 */
public interface MovieRepository extends Neo4jRepository<MovieEntity, String> {
    MovieEntity findOneByTitle(String title);

    long deleteAllByTitle(String title);
    // 改写法无法NoRootNodeMappingException
//    @Query("MATCH(n:Movie)-[r:ACTED_IN]-(p:Person) return r")
//    List<ActRelDetailDTO> findActRelationEntities();

    @Query("MATCH(n:Movie)-[r:ACTED_IN]-(p:Person) delete r")
    Long delActRelationEntities();

}
