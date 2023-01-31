package com.example.demoneo4j.domain.movie.service;

import com.example.demoneo4j.domain.movie.dao.MovieRepository;
import com.example.demoneo4j.domain.movie.model.MovieEntity;
import org.neo4j.driver.Driver;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangmengyue
 * @ClassName MovieService.java
 * @Description TODO
 * @createTime 2023年01月30日 19:07:00
 */
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private final Neo4jClient neo4jClient;
    private final Driver driver;
    private final DatabaseSelectionProvider databaseSelectionProvider;

    public MovieService(MovieRepository movieRepository,
                        Neo4jClient neo4jClient,
                        Driver driver,
                        DatabaseSelectionProvider databaseSelectionProvider) {
        this.movieRepository = movieRepository;
        this.neo4jClient = neo4jClient;
        this.driver = driver;
        this.databaseSelectionProvider = databaseSelectionProvider;
    }

//    public List<MovieEntity> findMoviesByAct(String actName){
//        return this.neo4jClient.query("");
//    }

    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    public MovieEntity findOneByTitle(String title) {
        return movieRepository.findOneByTitle(title);
    }

    public long delAllByTitle(String title) {
        return movieRepository.deleteAllByTitle(title);
    }

    public Long delActRelationEntities() {
        return movieRepository.delActRelationEntities();
    }

    public MovieEntity save(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    public List<MovieEntity> saveAll(List<MovieEntity> movies) {
        return movieRepository.saveAll(movies);
    }
}
