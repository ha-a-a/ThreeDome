package com.example.demoneo4j.interfaces.controller;

import com.example.demoneo4j.domain.movie.dao.MovieRepository;
import com.example.demoneo4j.domain.movie.model.MovieEntity;
import com.example.demoneo4j.domain.movie.service.MovieService;
import com.example.demoneo4j.domain.person.model.PersonEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tangmengyue
 * @ClassName MovieControllerTest.java
 * @Description TODO
 * @createTime 2023年01月30日 18:57:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class MovieControllerTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void getMovies() {
        List<MovieEntity> all = movieService.findAll();
        log.info("all movies={}",all);
    }

    @Test
    public void createMovie() {
        PersonEntity zhangyimo = PersonEntity.builder().name("张艺谋").born(19500402).build();
        Set<PersonEntity> directPerson = new HashSet<>();
        directPerson.add(zhangyimo);
        PersonEntity zhangyi = PersonEntity.builder().name("张译").born(19780217).build();
        Set<PersonEntity> actPerson = new HashSet<>();
        actPerson.add(zhangyi);
        MovieEntity movie = MovieEntity.builder()
                .actors(actPerson).directors(directPerson)
                .title("满江红").description("2023春节电影").build();
        MovieEntity movieEntity = movieService.save(movie);
        log.info("create movies={}",movieEntity);
    }
}