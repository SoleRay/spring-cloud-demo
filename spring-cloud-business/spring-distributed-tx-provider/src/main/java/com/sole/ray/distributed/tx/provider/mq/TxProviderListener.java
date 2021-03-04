package com.sole.ray.distributed.tx.provider.mq;

import com.alibaba.fastjson.JSONObject;
import com.sole.ray.distributed.tx.provider.param.Business;
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
public class TxProviderListener implements MessageListenerConcurrently {

    @Autowired
    private ProviderService providerService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        log.info("服务消费者线程监听到消息。");
        int x=1/0;
        try{
            for (MessageExt message:list) {
                log.info("消费消息中。。。");
                Business business  = JSONObject.parseObject(message.getBody(), Business.class);
                providerService.addProvider(business.getProvider());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }catch (Exception e){
            log.error("处理消费者数据发生异常。{}",e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}