package priv.tmy.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PullConsumerTest {

    @Autowired
    private PullConsumer pullConsumer;

    @Test
    public void receive() throws MQClientException, InterruptedException {
        pullConsumer.receive();
        Thread.sleep(10000);
    }
}