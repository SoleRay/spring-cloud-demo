package com.sole.ray.distributed.tx.provider.mq;

import com.sole.ray.distributed.tx.provider.config.props.RocketMQProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RocketMQConsumer {

    private static final String CONSUMER_GROUP = "consumer-group";

    private static final String TARGET_TOPIC = "order";

    private DefaultMQPushConsumer mqPushConsumer;

    @Autowired
    private RocketMQProperties rocketMQProperties;

    @Autowired
    private RocketMQConsumerListener rocketMQConsumerListener;
    
    @PostConstruct
    public void init() throws MQClientException {
        mqPushConsumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        mqPushConsumer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        mqPushConsumer.subscribe(TARGET_TOPIC,"*");
//        mqPushConsumer.registerMessageListener();

        // 2次失败 就进私信队列
        mqPushConsumer.setMaxReconsumeTimes(rocketMQProperties.getMaxReconsumeTimes());
        mqPushConsumer.start();
    }
}