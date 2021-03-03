package com.sole.ray.distributed.tx.provider.mq;

import com.sole.ray.distributed.tx.provider.service.ProviderService;
import com.sole.ray.internal.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RocketMQConsumerListener implements TransactionListener {

    @Autowired
    private ProviderService providerService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        LocalTransactionState state;

        try{
            String string = new String(message.getBody());
            JsonUtil.

//            providerService.addProvider();
        }


        return null;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return null;
    }
}