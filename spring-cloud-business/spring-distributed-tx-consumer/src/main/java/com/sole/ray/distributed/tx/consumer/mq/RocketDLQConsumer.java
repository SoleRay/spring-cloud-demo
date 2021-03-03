package com.sole.ray.distributed.tx.consumer.mq;

import com.sole.ray.distributed.tx.consumer.config.props.RocketMQProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RocketDLQConsumer {

    private static final String CONSUMER_DLQ_GROUP = "consumer-dlq-group";

    private static final String TARGET_TOPIC = "%DLQ%consumer-group";

    private DefaultMQPushConsumer mqPushConsumer;


    @Autowired
    private RocketMQProperties rocketMQProperties;

    @Autowired
    OrderListenerDld orderListener;
    
    @PostConstruct
    public void init() throws MQClientException {
        mqPushConsumer = new DefaultMQPushConsumer(CONSUMER_DLQ_GROUP);
        mqPushConsumer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        mqPushConsumer.subscribe(TARGET_TOPIC,"*");
        mqPushConsumer.registerMessageListener(orderListener);

        mqPushConsumer.setMaxReconsumeTimes(rocketMQProperties.getMaxReconsumeTimes());
        mqPushConsumer.start();
    }
}