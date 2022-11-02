package com.example.demo.mongo.service.impl;

import com.example.timeline.demo.mongo.pojo.bindingdata.MaterialInfo;
import com.example.demo.mongo.service.MaterialInfoRepo;
import com.example.demo.mongo.service.MaterialInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/15
 * @Desc
 */
@Service
public class MaterailInfoServiceImpl implements MaterialInfoService {
    @Autowired
    MaterialInfoRepo materialInfoRepo;

    @Override
    public MaterialInfo save(MaterialInfo entity) throws Exception {
        entity.getMaterial(entity.getId());
        materialInfoRepo.save(entity);
        return entity;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public MaterialInfo findById(String id) {
        return null;
    }

    @Override
    public List<MaterialInfo> findAll() {
        return null;
    }
}

