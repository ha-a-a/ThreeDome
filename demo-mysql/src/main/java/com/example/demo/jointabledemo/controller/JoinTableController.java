package com.example.demo.jointabledemo.controller;

import com.example.demo.jointabledemo.model.Address;
import com.example.demo.jointabledemo.model.Person;
import com.example.demo.jointabledemo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.validation.Valid;

@RestController
@RequestMapping("/join")
@Slf4j
public class JoinTableController {
    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public void add(@Valid @RequestBody Person person) throws Exception {

        personService.save(person);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable String id, @Valid @RequestBody Person person) throws Exception {
        personService.save(person);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        personService.delete(id);
    }

    @GetMapping("/list")
    public Page<Person> list(
            Person person,
            Address address,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "create_time") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        log.info("person:{},address:{}", person, address);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        Page<Person> all = personService.findAll((Specification<Person>) (root, criteriaQuery, criteriaBuilder) -> {

            Join<Person, Address> join = root.join("address", JoinType.INNER);
            Predicate predicate = null;
            if (null != address.getAddresses()) {
                predicate = criteriaBuilder.equal(join.get("addresses"), address.getAddresses());
            }
            return predicate;
        }, pageable);


        return all;
    }

    @GetMapping("/detail/{id}")
    public Person getDetail(@PathVariable Integer id) {
        log.info("getDetail");
        return personService.findById(id);
    }
}
