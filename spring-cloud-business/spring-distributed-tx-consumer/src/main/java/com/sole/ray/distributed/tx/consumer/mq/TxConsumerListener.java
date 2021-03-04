package com.sole.ray.distributed.tx.consumer.mq;

import com.sole.ray.distributed.tx.consumer.param.Business;
import com.sole.ray.distributed.tx.consumer.service.ConsumerService;
import com.sole.ray.distributed.tx.consumer.service.TransactionLogService;
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
public class TxConsumerListener implements TransactionListener {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private TransactionLogService transactionLogService;

    /**
     *
     * 当本服务使用MQClient的TransactionMQProducer向MQ发送half msg成功后
     * 回调此方法。
     *
     * 关于返回结果
     *     1.COMMIT_MESSAGE：说明当前事务执行成功，half msg的状态将会变为成功。可以供TX-Provider消费
     *     2.ROLLBACK_MESSAGE，说明当前事务执行失败，执行异常回滚。half msg的状态将会变为失败。此消息将不能被TX-Provider消费
     *     3.UNKNOW，不清楚当前事务执行的情况，可能是网络原因引起的。此时MQ将启动回查机制，调用checkLocalTransaction进行回查。
     *       3.1 如果回查时发生事务执行成功，则返回COMMIT_MESSAGE状态，half msg的状态将会变为成功。可以供TX-Provider消费
     *       3.2 如果回查时还是不清楚事务是否已经执行，将继续返回UNKNOW状态，继续等待回查。回查一定次数以后仍然无法确定，将停止回查。
     *
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {

        log.info("开始执行本地事务...");
        LocalTransactionState state;

        try {
            String json = new String(message.getBody());
            Business business = JsonUtil.convertJsonToJavaObject(json, Business.class);
            consumerService.addConsumer(business.getConsumer(),message.getTransactionId());

//            state = LocalTransactionState.COMMIT_MESSAGE;
            state = LocalTransactionState.UNKNOW;
        } catch (Exception e) {
            log.info("执行本地事务失败...");
            log.error(e.getMessage(),e);
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        log.info("开始回查本地事务:"+messageExt.getTransactionId());
        LocalTransactionState state;

        if (transactionLogService.selectById(messageExt.getTransactionId()) > 0) {
            log.info("ok!!!!");
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            log.info("unknow!!!!!");
            state = LocalTransactionState.UNKNOW;
        }

        log.info("结束本地事务回查...");

        return state;
    }
}