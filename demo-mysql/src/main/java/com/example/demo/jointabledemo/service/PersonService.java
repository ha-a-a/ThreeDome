package com.example.demo.jointabledemo.service;

import com.example.demo.common.ICommonService;
import com.example.demo.jointabledemo.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PersonService extends ICommonService<Person, Integer> {
    Page<Person> findAll(Specification<Person> specification, Pageable pageRequest);

    List<Person> getAllPersonByName(String name);

}
