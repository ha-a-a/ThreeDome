package com.example.demo.jointabledemo.service.impl;

import com.example.demo.jointabledemo.dao.PersonRepo;
import com.example.demo.jointabledemo.model.Person;
import com.example.demo.jointabledemo.model.QPerson;
import com.example.demo.jointabledemo.service.PersonService;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepo personRepo;

    @Override
    public Person save(Person entity) throws Exception {
        return personRepo.save(entity);
    }

    @Override
    public void delete(Integer id) throws Exception {
        personRepo.deleteById(id);
    }

    @Override
    public Person findById(Integer id) {
        return personRepo.findById(id).orElse(null);
    }

    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public Page<Person> findAll(Specification<Person> specification, Pageable pageable) {
        return personRepo.findAll(specification, pageable);
    }
    @Override
    @Cacheable(cacheNames = "allPerson",key = "#name")
    public List<Person> getAllPersonByName(String name){
        Predicate predicate=QPerson.person.name.eq(name);
        return Lists.newArrayList(personRepo.findAll(predicate));
    }

}
