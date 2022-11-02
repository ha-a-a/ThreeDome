package priv.tmy.rocketmq.producer;

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
public class SendMsgServiceTest {

    @Autowired
    private SendMsgService sendMsgService;

    @Test
    public void send() throws MQClientException {
        sendMsgService.sendSyncMsg("tmy message");
    }
}