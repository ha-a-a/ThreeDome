package com.example.demo.mode.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/14
 * @Desc
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommandTest {
    @Test
    public void commandTest(){
        // 设计模式之命令模式
        List commands = new ArrayList();
        commands.add(new Engineer());
        commands.add(new Politician());
        commands.add(new Programmer());
        for (Iterator it = commands.iterator(); it.hasNext();){
            ((Command)it.next()).execute();
        }
    }
}