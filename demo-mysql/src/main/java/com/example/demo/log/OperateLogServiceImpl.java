package com.example.demo.log;

import com.example.demo.log.pojo.OperateLogPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    OperateLogRepo operateLogRepo;

    @Override
    public OperateLogPojo save(OperateLogPojo entity) throws Exception {
        return operateLogRepo.save(entity);
    }

    @Override
    public void delete(Integer id) throws Exception {
        operateLogRepo.deleteById(id);
    }

    @Override
    public OperateLogPojo findById(Integer id) {
        return operateLogRepo.findById(id).orElse(null);
    }

    @Override
    public List<OperateLogPojo> findAll() {
        return operateLogRepo.findAll();
    }
}
