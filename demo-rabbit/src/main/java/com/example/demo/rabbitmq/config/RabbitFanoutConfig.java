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
public class RabbitFanoutConfig {
    @Bean
    public Queue aFanoutQueue() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue bFanoutQueue() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue cFanoutQueue() {
        return new Queue("fanout.C");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingExchangeA(Queue aFanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(aFanoutQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutExchangeB(Queue bFanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(bFanoutQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bingingFanoutExchangeC(Queue cFanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(cFanoutQueue).to(fanoutExchange);
    }
}
