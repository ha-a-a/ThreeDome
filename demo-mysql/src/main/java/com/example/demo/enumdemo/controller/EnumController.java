package com.example.demo.enumdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.enumdemo.model.AccountBlackList;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/6/18
 * @Desc
 */
@RestController
@RequestMapping("/")
@Slf4j
public class EnumController {
    @GetMapping("enum")
    public void list(@PageableDefault(sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable,
                     @QuerydslPredicate(root = AccountBlackList.class) Predicate predicate) {
        log.info("predicate:{}", JSON.toJSONString(predicate));
    }
}
