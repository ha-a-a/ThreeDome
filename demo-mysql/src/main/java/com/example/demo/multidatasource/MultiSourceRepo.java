package com.example.demo.multidatasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@Repository
public interface MultiSourceRepo extends JpaRepository<MultiSourceData,Integer> {

}
