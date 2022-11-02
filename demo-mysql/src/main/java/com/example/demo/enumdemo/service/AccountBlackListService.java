package com.example.demo.enumdemo.service;

import com.example.demo.enumdemo.model.AccountBlackList;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountBlackListService {

    /**
     * query dsl
     * @param predicate
     * @param pageRequest
     * @return page
     */
    Page<AccountBlackList> findAll(Predicate predicate, Pageable pageRequest);

}
