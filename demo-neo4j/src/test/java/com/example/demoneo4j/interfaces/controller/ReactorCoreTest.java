package com.example.demoneo4j.interfaces.controller;

import com.example.demoneo4j.domain.person.model.PersonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author tangmengyue
 * @ClassName ReactorCoreTest.java
 * @Description TODO
 * @createTime 2023年03月08日 15:41:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReactorCoreTest {
    @Test
    public void testMono() throws InterruptedException {
        Mono.just(PersonEntity.builder().name("tmy").born(202301).build()).map(PersonEntity::getName).filter(s -> s.contains("tmy")).subscribe(System.out::println);
        Thread.sleep(10000);
    }

    @Test
    public void testFlux() throws InterruptedException {
        Flux<PersonEntity> just = Flux.just(PersonEntity.builder().name("tmy1").born(202301).build(),
                        PersonEntity.builder().name("tmnny2").born(202302).build(),
                        PersonEntity.builder().name("tmy3").born(202303).build(),
                        PersonEntity.builder().name("tmy3").born(202303).build())
                .filter(s -> s.getName().contains("tmy"))
                .map(s -> {
                    s.setBorn(s.getBorn() + 10);
                    return s;
                })
                .distinct(PersonEntity::getName);
        just.subscribe(s -> {
            System.out.println(s.getName() + "——" + s.getBorn());
        });
        Thread.sleep(10000);
    }
}