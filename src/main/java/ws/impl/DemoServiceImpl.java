package ws.impl;
//import lombok.extern.log4j.Log4j;
//import lombok.extern.log4j.Log4j2;
import mq.MyConsumer;
import mq.MyProducer;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;


import ws.DemoService;

import javax.jws.WebService;
import java.util.Date;
//@Log4j2
public class DemoServiceImpl implements DemoService {
    private final Logger log = Logger.getLogger(DemoServiceImpl.class);
    private MyProducer myProducer;
    @Override
    public String sayHello(String user) {
        Message msg = new Message("MyTopic", "MyTag", (user).getBytes());
        SendResult sendResult = null;
        try {
            sendResult = myProducer.getDefaultMQProducer().send(msg);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            log.error(e.getMessage() + String.valueOf(sendResult));
        }
        // 当消息发送失败时如何处理
        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
            log.error("发送失败");
            // TODO
        }else {
            log.info("发送成功");
        }
        return "发送成功";
    }
    public void setMyProducer(MyProducer myProducer) {
        this.myProducer = myProducer;
    }

}
