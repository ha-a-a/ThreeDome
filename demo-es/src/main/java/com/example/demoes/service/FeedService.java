package com.example.demoes.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demoes.dao.FeedRepository;
import com.example.demoes.model.Feed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangmengyue
 * @ClassName FeedService.java
 * @Description TODO
 * @createTime 2023年02月14日 14:09:00
 */
@Service
@Slf4j
public class FeedService {
    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public Feed searchFeedByFeedId(String feedId) {
        Feed byId = feedRepository.findByFeedId(feedId);
        log.info("feed={}", JSONObject.toJSONString(byId));
        return byId;
    }

    public List<Feed> finaAllByUid(String uid) {
        List<Feed> byId = feedRepository.findAllByUserId(uid);
        log.info("search feed by user, feed={}", JSONObject.toJSONString(byId));
        return byId;
    }
}
