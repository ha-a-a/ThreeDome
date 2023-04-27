package com.example.demo.mongo.repo;

import com.example.demo.mongo.pojo.user.TeacherInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tangmengyue
 * @ClassName StudentRepo.java
 * @Description TODO
 * @createTime 2023年04月26日 11:12:00
 */
@Repository
public interface TeacherRepo extends MongoRepository<TeacherInfo, String> {
    TeacherInfo findFirstByName(String name);
}
