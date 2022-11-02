package com.example.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/14
 * @Desc
 */
@Configuration
public class RabbitTopicConfig {
    public static final String  message="topic.message";
    public static final String  messages="topic.messages";
    @Bean
    public Queue aTopicQueue() {
        return new Queue(RabbitTopicConfig.message);
    }

    @Bean
    public Queue bTopicQueue() {
        return new Queue(RabbitTopicConfig.messages);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingTopicExchangeA(Queue aTopicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(aTopicQueue).to(topicExchange).with(RabbitTopicConfig.message);
    }

    @Bean
    public Binding bindingTopicExchangeB(Queue bTopicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(bTopicQueue).to(topicExchange).with("topic.#");
    }
}
