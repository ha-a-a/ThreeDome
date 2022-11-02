package com.example.demo.service.impl;

import com.example.demo.model.TbOrder;
import com.example.demo.dao.TbOrderRepo;
import com.example.demo.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Autowired
    TbOrderRepo tbOrderRepo;
    @Override
    public TbOrder save(TbOrder tbOrder) {
        return tbOrderRepo.save(tbOrder);
    }

    @Override
    public void deleteById(Integer id) {
        tbOrderRepo.deleteById(id);
    }

    @Override
    public List<TbOrder> saveAll(List<TbOrder> tbOrder) {
        return tbOrderRepo.saveAll(tbOrder);
    }

    @Override
    public TbOrder findById(Integer id) {
        return tbOrderRepo.findById(id).orElse(null);
    }

    @Override
    public List<TbOrder> findAll() {
        return tbOrderRepo.findAll();
    }
}
