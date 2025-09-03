package com.example.demoneo4j.interfaces.controller;

import com.example.demoneo4j.domain.movie.dao.MovieRepository;
import com.example.demoneo4j.domain.movie.model.MovieEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tangmengyue
 * @ClassName MovieController.java
 * @Description TODO
 * @createTime 2023年01月30日 10:50:00
 */
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PutMapping("/add")
    public MovieEntity createMovie(@RequestBody MovieEntity addMovie) {
        return movieRepository.save(addMovie);
    }

    @GetMapping("/all")
    public List<MovieEntity> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/byTitle")
    public MovieEntity findByTitle(String title) {
        return movieRepository.findOneByTitle(title);
    }

    @DeleteMapping("/del/{title}")
    public void delete(@PathVariable String title) {
        movieRepository.deleteById(title);
    }
}