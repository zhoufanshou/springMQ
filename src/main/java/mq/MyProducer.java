package mq;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import ws.impl.DemoServiceImpl;



//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//@Log4j2
public class MyProducer {
    private final Logger log = Logger.getLogger(MyProducer.class);
    private DefaultMQProducer defaultMQProducer;
    private String producerGroup;
    private String namesrvAddr;
    MyProducer(){
    }
    MyProducer(String producerGroup,String namesrvAddr){
        this.producerGroup=producerGroup;
        this.namesrvAddr=namesrvAddr;
    }

    /**
     * Spring bean init-method
     */
    public void init()  {
        // 参数信息
        log.info("DefaultMQProducer initialize!");
        log.info(producerGroup);
        log.info(namesrvAddr);

        // 初始化
        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        defaultMQProducer.setInstanceName(String.valueOf(System.currentTimeMillis()));

        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        log.info("DefaultMQProudcer start success!");

    }

    /**
     * Spring bean destroy-method
     */
    public void destroy() {
        defaultMQProducer.shutdown();
    }

    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }

    // ---------------setter -----------------

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

}