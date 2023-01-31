package com.example.demoneo4j.domain.movie.service;

import com.example.demoneo4j.domain.movie.model.ActRelationEntity;
import com.example.demoneo4j.domain.movie.model.MovieEntity;
import com.example.demoneo4j.domain.movie.model.MovieEntityDTO;
import com.example.demoneo4j.domain.person.model.PersonEntity;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tangmengyue
 * @ClassName MovieControllerTest.java
 * @Description TODO
 * @createTime 2023年01月30日 18:57:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void getMoviesByOmg() {
        List<MovieEntity> all = movieService.findAll();
        log.info("all movies={}", all);
    }

    @Test
    public void getMoviesByNeo4jClient() throws JsonProcessingException {
        MovieEntityDTO movieByTitle = movieService.getMovieByTitle("满江红");
        log.info("by title movie={}", new ObjectMapper().writeValueAsString(movieByTitle));
    }


    @Test
    public void getMoviesBySession() {
        Map<String, List<Object>> all = movieService.getMovieGraph();
        log.info("all movies={}", JSONObject.wrap(all).toString());
    }
    @Test
    public void getMoviesByTitle() {
        MovieEntity movie = movieService.findOneByTitle("满江红");
        log.info("get movie by title={}", movie);
    }

    @Test
    public void delMoviesByTitle() {
        long count = movieService.delAllByTitle("我和我的父辈");
        log.info("del movies={}", count);
    }

    @Test
    public void delActRelations() {
        Long actRelationEntities = movieService.delActRelationEntities();
        log.info("del actRelations={}", actRelationEntities);
    }

    @Test
    public void initMovie() {
        List<MovieEntity> movies = new ArrayList<>();

        // 满江红
        Set<PersonEntity> manjianghongDirect = new HashSet<>();
        manjianghongDirect.add(PersonEntity.builder().name("张艺谋").born(19500402).build());
        Set<ActRelationEntity> manjianghongAct = new HashSet<>();
        manjianghongAct.add(ActRelationEntity.builder().role("何立").person(PersonEntity.builder().name("张译").born(19780217).build()).build());
        manjianghongAct.add(ActRelationEntity.builder().role("孙珺").person(PersonEntity.builder().name("易烊千玺").born(20001128).build()).build());
        manjianghongAct.add(ActRelationEntity.builder().role("张大").person(PersonEntity.builder().name("沈腾").born(19791023).build()).build());
        manjianghongAct.add(ActRelationEntity.builder().role("武义淳").person(PersonEntity.builder().name("岳云鹏").born(19850415).build()).build());
        manjianghongAct.add(ActRelationEntity.builder().role("王彪").person(PersonEntity.builder().name("郭京飞").born(19790702).build()).build());
        manjianghongAct.add(ActRelationEntity.builder().role("秦桧").person(PersonEntity.builder().name("雷佳音").born(19830829).build()).build());

        movies.add(MovieEntity.builder()
                .actors(manjianghongAct).directors(manjianghongDirect)
                .title("满江红").description("2023春节悬疑喜剧电影").build());

        // 流浪地球2
        Set<PersonEntity> liulangDirect = new HashSet<>();
        liulangDirect.add(PersonEntity.builder().name("郭凡").born(19801215).build());
        Set<ActRelationEntity> liulangAct = new HashSet<>();
        liulangAct.add(ActRelationEntity.builder().role("刘培强").person(PersonEntity.builder().name("吴京").born(19740403).build()).build());
        liulangAct.add(ActRelationEntity.builder().role("张鹏").person(PersonEntity.builder().name("沙溢").born(19780215).build()).build());
        liulangAct.add(ActRelationEntity.builder().role("图恒宇").person(PersonEntity.builder().name("刘德华").born(19610927).build()).build());

        movies.add(MovieEntity.builder()
                .actors(liulangAct).directors(liulangDirect)
                .title("流浪地球2").description("2023春节灾难电影").build());

        // 交换人生
        Set<PersonEntity> jiaohuanDirect = new HashSet<>();
        jiaohuanDirect.add(PersonEntity.builder().name("苏伦").born(19801017).build());
        Set<ActRelationEntity> jiaohuanAct = new HashSet<>();
        jiaohuanAct.add(ActRelationEntity.builder().role("仲达").person(PersonEntity.builder().name("雷佳音").born(19830829).build()).build());
        jiaohuanAct.add(ActRelationEntity.builder().role("陈小谷爸爸").person(PersonEntity.builder().name("沙溢").born(19780215).build()).build());
        jiaohuanAct.add(ActRelationEntity.builder().role("金好").person(PersonEntity.builder().name("张小斐").born(19860110).build()).build());

        movies.add(MovieEntity.builder()
                .actors(jiaohuanAct).directors(jiaohuanDirect)
                .title("交换人生").description("2023春节亲情家庭电影").build());

        // 我和我的父辈
        Set<PersonEntity> fubeiDirect = new HashSet<>();
        fubeiDirect.add(PersonEntity.builder().name("吴京").born(19740403).build());
        fubeiDirect.add(PersonEntity.builder().name("章子怡").born(19790209).build());
        fubeiDirect.add(PersonEntity.builder().name("沈腾").born(19791023).build());
        fubeiDirect.add(PersonEntity.builder().name("徐峥").born(19720418).build());
        Set<ActRelationEntity> fubeiAct = new HashSet<>();
        fubeiAct.add(ActRelationEntity.builder().role("马仁兴").person(PersonEntity.builder().name("吴京").born(19740403).build()).build());
        fubeiAct.add(ActRelationEntity.builder().role("郁凯迎").person(PersonEntity.builder().name("章子怡").born(19790209).build()).build());
        fubeiAct.add(ActRelationEntity.builder().role("邢一浩").person(PersonEntity.builder().name("沈腾").born(19791023).build()).build());
        fubeiAct.add(ActRelationEntity.builder().role("赵平洋").person(PersonEntity.builder().name("徐峥").born(19720418).build()).build());


        movies.add(MovieEntity.builder()
                .actors(fubeiAct).directors(fubeiDirect)
                .title("我和我的父辈").description("2021多段式剧情电影").build());
        //节点和directed关系会被merge，因为关系中没有属性，
        // 但是关系acted_in关系会再次create，因为该关系中有属性，为了保证属性的唯一性，需要自动创建id，所以这种save方式会acted_in会被多次生成
        List<MovieEntity> movieEntity = movieService.saveAll(movies);
        log.info("create movies={}", movieEntity);
    }

    @Test
    public void testUpdateActRelProperties() {
        MovieEntity movie = movieService.findOneByTitle("满江红");
        movie.getActors().forEach(t -> {
            t.setRole(t.getRole() + "1");
        });
        MovieEntity save = movieService.save(movie);
        log.info("update act rel, movie={}", save);
    }
    @Test
    public void testVoteInMovieByTitle(){
        int voteInMovieByTitle = movieService.voteInMovieByTitle("满江红");
        log.info("vote movie {}",voteInMovieByTitle==1);
    }
}