package com.example.demo.mongo.service;

import com.example.demo.mongo.pojo.user.TeacherInfo;
import com.example.demo.mongo.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangmengyue
 * @ClassName StudentService.java
 * @Description TODO
 * @createTime 2023年04月26日 11:13:00
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;

    public TeacherInfo insert(TeacherInfo teacherInfo) {
        return teacherRepo.insert(teacherInfo);
    }

    public TeacherInfo findOneByName(String name) {
        return teacherRepo.findFirstByName(name);
    }


    public TeacherInfo updateWithVersion(TeacherInfo teacherInfo) {
      return teacherRepo.save(teacherInfo);
    }
}
