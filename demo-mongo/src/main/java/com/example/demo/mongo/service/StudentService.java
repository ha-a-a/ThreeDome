package com.example.demo.mongo.service;

import com.example.demo.mongo.pojo.user.StudentInfo;
import com.example.demo.mongo.pojo.user.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tangmengyue
 * @ClassName StudentService.java
 * @Description TODO
 * @createTime 2023年04月26日 11:13:00
 */
@Service
public class StudentService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(transactionManager = "mongoTransactionManager", rollbackFor = Exception.class)
    public void insertWithTransaction(List<StudentInfo> studentInfos) {
        for (StudentInfo studentInfo : studentInfos) {
            mongoTemplate.insert(studentInfo, "StudentInfo");
            mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(studentInfo.getTeacherId())), new Update().inc("studentCounts", 1), TeacherInfo.class);
        }
    }

    public void insert(StudentInfo studentInfo) {
        mongoTemplate.insert(studentInfo, "StudentInfo");
    }

}
