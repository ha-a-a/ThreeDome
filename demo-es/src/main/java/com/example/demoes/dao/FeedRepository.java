package com.example.demoes.dao;

import com.example.demoes.model.Feed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tangmengyue
 * @ClassName FeedRepository.java
 * @Description TODO
 * @createTime 2023年02月14日 11:29:00
 */
@Repository
public interface FeedRepository extends ElasticsearchRepository<Feed, String> {
    Feed findByFeedId(String feedIds);

    List<Feed> findAllByUserId(String uid);
}
