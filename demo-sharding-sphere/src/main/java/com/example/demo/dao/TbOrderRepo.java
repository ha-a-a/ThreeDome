package com.example.demo.dao;

import com.example.demo.model.TbOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
@Repository
public interface TbOrderRepo extends JpaRepository<TbOrder, Integer> {
}
