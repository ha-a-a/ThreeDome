package com.example.demo.multidatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@RestController
@RequestMapping("/datasource")
public class MultiSourceDataController {
    @Autowired
    MultiSourceService multiSourceService;
    @GetMapping("/get/{id}")
    public MultiSourceData get(@PathVariable Integer id){
        return multiSourceService.findById(id);
    }
}
