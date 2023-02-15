package com.example.demoes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tangmengyue
 * @ClassName FeedServiceTest.java
 * @Description TODO
 * @createTime 2023年02月14日 15:52:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FeedServiceTest {
    @Autowired
    private FeedService feedService;

    @Test
    public void searchFeedByFeedId() {
        feedService.searchFeedByFeedId("1001540365418957824");
    }
    @Test
    public void searchFeedByUid() {
        feedService.finaAllByUid("629361223439325312");
    }
}