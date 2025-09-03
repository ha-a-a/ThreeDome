package com.example.demo.mongo.service;

import com.example.demo.mongo.pojo.autoinc.SeqInfo;
import com.example.demo.mongo.pojo.autoinc.annotation.AutoIncKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
@Component
public class SaveEventListener extends AbstractMongoEventListener<Object> {
    public static final Logger logger = LoggerFactory.getLogger(SaveEventListener.class);
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        logger.info("source:{}", source);
        if (null != source) {
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                logger.info("field:{}", field);
                ReflectionUtils.makeAccessible(field);
                if (field.isAnnotationPresent(AutoIncKey.class)&& field.get(source) instanceof Number
                        && field.getLong(source) == 0) {
                    field.set(source, getNextId(source.getClass().getSimpleName()));
                }
            });
        }
    }

    private long getNextId(String simpleName) {
        logger.info("simpleName:{}", simpleName);
        Query query = new Query(Criteria.where("simpleName").is(simpleName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SeqInfo info = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
        logger.info("object.getSeqId():{}", info.getSeqId());
        return info.getSeqId();
    }
}
