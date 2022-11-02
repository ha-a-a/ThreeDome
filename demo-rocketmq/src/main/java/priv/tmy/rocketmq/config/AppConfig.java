package priv.tmy.rocketmq.config;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Bean("rocketmqProducer")
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("tmy-rocketMQ-producer-group");
        defaultMQProducer.setNamesrvAddr("10.16.76.146:9876");
        defaultMQProducer.start();
        return defaultMQProducer;
    }

}
