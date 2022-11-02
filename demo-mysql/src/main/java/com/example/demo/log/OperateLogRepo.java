package com.example.demo.log;

import com.example.demo.log.pojo.OperateLogPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
@Repository
public interface OperateLogRepo extends JpaRepository<OperateLogPojo, Integer>, QuerydslPredicateExecutor {
}
