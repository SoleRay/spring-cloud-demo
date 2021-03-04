package com.sole.ray.distributed.tx.provider.mq;

import com.sole.ray.distributed.tx.provider.config.MQConsumerConstant;
import com.sole.ray.distributed.tx.provider.config.props.MQConsumerProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DLQConsumer {

    private DefaultMQPushConsumer dlqConsumer;

    @Autowired
    private MQConsumerProperties mqConsumerProperties;

    @Autowired
    private TxProviderListenerDLQ orderListener;

    @PostConstruct
    public void init() throws MQClientException {
        dlqConsumer = new DefaultMQPushConsumer(MQConsumerConstant.DLQ_CONSUMER_GROUP);
        dlqConsumer.setNamesrvAddr(mqConsumerProperties.getNamesrvAddr());
        dlqConsumer.subscribe(MQConsumerConstant.DLQ_CONSUMER_TOPIC,"*");
        dlqConsumer.registerMessageListener(orderListener);

        dlqConsumer.setMaxReconsumeTimes(mqConsumerProperties.getMaxReconsumeTimes());
        dlqConsumer.start();
    }
}