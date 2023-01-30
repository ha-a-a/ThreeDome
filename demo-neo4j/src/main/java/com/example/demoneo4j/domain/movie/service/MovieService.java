package com.example.demoneo4j.domain.movie.service;

import com.example.demoneo4j.domain.movie.dao.MovieRepository;
import com.example.demoneo4j.domain.movie.model.MovieEntity;
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
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    public MovieEntity save(MovieEntity movie) {
        return movieRepository.save(movie);
    }
}
