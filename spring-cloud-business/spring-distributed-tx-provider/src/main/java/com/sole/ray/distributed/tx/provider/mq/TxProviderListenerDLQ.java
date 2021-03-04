package com.sole.ray.distributed.tx.provider.mq;

import com.sole.ray.distributed.tx.provider.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TxProviderListenerDLQ implements MessageListenerConcurrently {

    @Autowired
    private ProviderService providerService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        log.info("死信队列：消费者线程监听到消息。");
        try{
            for (MessageExt message:list) {
                log.info("死信队列：开始处理订单数据，准备增加积分....");

                System.out.println("发邮件");

            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }catch (Exception e){
            log.error("死信队列：处理消费者数据发生异常。{}",e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}