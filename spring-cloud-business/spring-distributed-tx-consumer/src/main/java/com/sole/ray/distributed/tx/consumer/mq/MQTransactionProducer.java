package com.sole.ray.distributed.tx.consumer.mq;

import com.sole.ray.distributed.tx.consumer.config.props.MQProducerProperties;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class MQTransactionProducer {
    
    private static final String PRODUCER_GROUP = "tx_consumer_group";

    // 事务消息
    private TransactionMQProducer producer;

    //用于执行本地事务和事务状态回查的监听器
    @Autowired
    private TxConsumerListener txConsumerListener;

    @Autowired
    private MQProducerProperties mqProducerProperties;

    //执行任务的线程池
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));
            
    @PostConstruct
    public void init(){
        producer = new TransactionMQProducer(PRODUCER_GROUP);
        producer.setNamesrvAddr(mqProducerProperties.getNamesrvAddr());
        producer.setSendMsgTimeout(Integer.MAX_VALUE);
        producer.setExecutorService(executor);
        producer.setTransactionListener(txConsumerListener);
        this.start();
    }

    private void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
    //事务消息发送 
    public TransactionSendResult send(String data, String topic) throws MQClientException {
        Message message = new Message(topic,data.getBytes());
        return this.producer.sendMessageInTransaction(message, null);
    }
}