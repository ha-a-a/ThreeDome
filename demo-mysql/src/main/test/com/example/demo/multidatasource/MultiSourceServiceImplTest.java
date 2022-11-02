package com.example.demo.multidatasource;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MultiSourceServiceImplTest {

    @Autowired
    MultiSourceService multiSourceService;
    @Test
    public void save() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void findById() {
        Integer id=1;
        MultiSourceData multiSourceData=multiSourceService.findById(id);
        log.info("multiSourceData:{}", JSONObject.toJSONString(multiSourceData));
    }

    @Test
    public void findAll() {
    }
}