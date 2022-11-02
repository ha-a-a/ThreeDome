package com.example.demo.enumdemo.service.impl;

import com.example.demo.enumdemo.model.AccountBlackList;
import com.example.demo.enumdemo.dao.AccountBlackListRepo;
import com.example.demo.enumdemo.service.AccountBlackListService;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author kevinzou
 */
@Slf4j
@Service
public class AccountBlackListServiceImpl implements AccountBlackListService {
    @Autowired
    private AccountBlackListRepo accountBlackListRepo;;
    @Override
    public Page<AccountBlackList> findAll(Predicate predicate, Pageable pageRequest) {
        return accountBlackListRepo.findAll(predicate, pageRequest);
    }}
