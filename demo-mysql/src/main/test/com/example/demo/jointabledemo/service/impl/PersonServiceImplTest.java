package com.example.demo.jointabledemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.jointabledemo.model.Person;
import com.example.demo.jointabledemo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;
    @Test
    public void testCacheDefaultName(){
        List<Person> tmy = personService.getAllPersonByName("tmy");
        log.info("tmy:{}", JSON.toJSONString(tmy));
        List<Person> ttttt = personService.getAllPersonByName("ttttt");
        log.info("ttttt:{}",ttttt);
        List<Person> tmy1 = personService.getAllPersonByName("tmy");
        log.info("tmy1:{}",tmy1);
        List<Person> ttttt1 = personService.getAllPersonByName("ttttt");
        log.info("ttttt:{}",ttttt1);
    }
}