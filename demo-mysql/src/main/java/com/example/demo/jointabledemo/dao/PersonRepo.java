package com.example.demo.jointabledemo.dao;

import com.example.demo.jointabledemo.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PersonRepo extends JpaRepository<Person,Integer> ,QuerydslPredicateExecutor<Person> {
    Page<Person> findAll(Specification<Person> specification, Pageable pageable);
}
