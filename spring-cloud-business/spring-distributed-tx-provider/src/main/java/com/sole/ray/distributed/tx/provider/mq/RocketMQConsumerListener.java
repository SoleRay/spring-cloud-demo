package com.sole.ray.distributed.tx.provider.mq;

import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.distributed.tx.provider.service.ProviderService;
import com.sole.ray.distributed.tx.provider.service.TransactionLogService;
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

    @Autowired
    private TransactionLogService transactionLogService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {

        log.info("开始执行本地事务...");
        LocalTransactionState state;

        try {
            String json = new String(message.getBody());
            Provider provider = JsonUtil.convertJsonToJavaObject(json, Provider.class);
            providerService.addProvider(provider);

            state = LocalTransactionState.COMMIT_MESSAGE;
//            state = LocalTransactionState.UNKNOW;
        } catch (Exception e) {
            log.info("执行本地事务失败...");
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        log.info("开始回查本地事务...");
        LocalTransactionState state;

        String transactionId = messageExt.getTransactionId();
        if (transactionLogService.selectById(transactionId) > 0) {
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            state = LocalTransactionState.UNKNOW;
        }

        log.info("结束本地事务回查...");

        return state;
    }
}