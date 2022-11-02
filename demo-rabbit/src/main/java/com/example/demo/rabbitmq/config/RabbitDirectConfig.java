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
public class RabbitDirectConfig {
    public static final String  message="direct.message";
    public static final String  messages="direct.messages";
    @Bean
    public Queue aDirectQueue() {
        return new Queue(RabbitDirectConfig.message);
    }

    @Bean
    public Queue bDirectQueue() {
        return new Queue(RabbitDirectConfig.messages);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindingDirectExchangeA(Queue aDirectQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(aDirectQueue).to(directExchange).with(RabbitDirectConfig.message);
    }

    @Bean
    public Binding bindingDirectExchangeB(Queue bDirectQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(bDirectQueue).to(directExchange).with(RabbitDirectConfig.message);
    }
}
