package com.sole.ray.distributed.tx.consumer.mq;

import com.sole.ray.distributed.tx.consumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class TxConsumerListener implements TransactionListener {

    private ConsumerService consumerService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        consumerService.doBusiness();
        return null;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return null;
    }
}