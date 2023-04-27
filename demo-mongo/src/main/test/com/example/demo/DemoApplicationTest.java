package com.example.demo;

import com.example.demo.mongo.pojo.user.StudentInfo;
import com.example.demo.mongo.pojo.user.TeacherInfo;
import com.example.demo.mongo.service.StudentService;
import com.example.demo.mongo.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangmengyue
 * @ClassName DemoApplicationTest.java
 * @Description TODO
 * @createTime 2023年04月26日 11:34:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTest {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @Test
    public void testInit() {
        TeacherInfo tttt = TeacherInfo.builder().name("tea1").age(30).subjectId(1).build();
        TeacherInfo insert = teacherService.insert(tttt);
        StudentInfo stu5 = StudentInfo.builder().name("stu5").ranking(30).teacherId(insert.getId()).build();
        studentService.insert(stu5);
    }

    @Test
    public void testInsertWithTransaction() {

        TeacherInfo tea1 = teacherService.findOneByName("tea1");
        List<StudentInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "stu1" + i;
            if (i == 5) {
                name = "stu1" + (i - 1);
            }
            list.add(StudentInfo.builder().name(name).teacherId(tea1.getId()).ranking(100).build());
        }
        studentService.insertWithTransaction(list);
    }

    @Test
    public void testUpdateWithVersion() {
        TeacherInfo tea2 = teacherService.insert(TeacherInfo.builder().name("tea2").age(30).subjectId(1).build());
        TeacherInfo tea2_v0 = teacherService.findOneByName("tea2");
        System.out.println("tea2_v0=" + tea2_v0);

        tea2.setAge(26);
        // version=0->1
        teacherService.updateWithVersion(tea2);
        TeacherInfo tea2_v1 = teacherService.findOneByName("tea2");
        System.out.println("tea2_v1=" + tea2_v1);

        tea2_v0.setAge(28);
        // version = 0 -> 不执行
        teacherService.updateWithVersion(tea2_v0);
        TeacherInfo tea2_v2 = teacherService.findOneByName("tea2");
        System.out.println("tea2_v2=" + tea2_v2);
    }


}