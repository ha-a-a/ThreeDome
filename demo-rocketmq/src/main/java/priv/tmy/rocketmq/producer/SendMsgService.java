package priv.tmy.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMsgService {
    @Autowired
    @Qualifier("rocketmqProducer")
    private DefaultMQProducer defaultMQProducer;

    /**
     * 发送同步消息
     */
    public void sendSyncMsg(String msg) {
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("tmy_test");
            message.setTags("TMY");
            msg = msg + i;
            message.setBody(msg.getBytes(CharsetUtil.UTF_8));
            try {
                SendResult sendResult = defaultMQProducer.send(message);
                log.info("send finish. sendResult={}", JSON.toJSONString(sendResult));
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
