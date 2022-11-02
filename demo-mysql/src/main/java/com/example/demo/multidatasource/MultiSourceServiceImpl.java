package com.example.demo.multidatasource;

import com.example.demo.multidatasource.config.DataSourceAnnotation;
import com.example.demo.multidatasource.config.DataSourceNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@Service
@DataSourceAnnotation(value = DataSourceNames.MESSAGE)
public class MultiSourceServiceImpl implements MultiSourceService {
    @Autowired
    MultiSourceRepo repo;

    @Override
    public MultiSourceData save(MultiSourceData entity) throws Exception {
        return repo.save(entity);
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public MultiSourceData findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<MultiSourceData> findAll() {
        return repo.findAll();
    }
}
