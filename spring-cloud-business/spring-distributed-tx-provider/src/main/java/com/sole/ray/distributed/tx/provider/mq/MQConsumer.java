package com.sole.ray.distributed.tx.provider.mq;

import com.sole.ray.distributed.tx.provider.config.MQConsumerConstant;
import com.sole.ray.distributed.tx.provider.config.props.MQConsumerProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MQConsumer {

    private DefaultMQPushConsumer mqConsumer;

    @Autowired
    private TxProviderListener txProviderListener;

    @Autowired
    private MQConsumerProperties mqConsumerProperties;

    @PostConstruct
    public void init() throws MQClientException {
        mqConsumer = new DefaultMQPushConsumer(MQConsumerConstant.MQ_CONSUMER_GROUP);
        mqConsumer.setNamesrvAddr(mqConsumerProperties.getNamesrvAddr());
        mqConsumer.subscribe(MQConsumerConstant.MQ_PRODUCER_TOPIC,"*");
        mqConsumer.registerMessageListener(txProviderListener);

        // 2次失败 就进私信队列
        mqConsumer.setMaxReconsumeTimes(mqConsumerProperties.getMaxReconsumeTimes());
        mqConsumer.start();
    }
}