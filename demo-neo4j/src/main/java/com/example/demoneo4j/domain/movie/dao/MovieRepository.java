package com.example.demoneo4j.domain.movie.dao;

import com.example.demoneo4j.domain.movie.model.MovieEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author tangmengyue
 * @ClassName MovieRepository.java
 * @Description TODO
 * @createTime 2023年01月30日 10:42:00
 */
public interface MovieRepository extends Neo4jRepository<MovieEntity, String> {
    MovieEntity findOneByTitle(String title);
}
