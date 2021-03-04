package com.sole.ray.distributed.tx.consumer.service.impl;

import com.alibaba.fastjson.JSON;
import com.sole.ray.distributed.tx.consumer.config.MQProduerConstant;
import com.sole.ray.distributed.tx.consumer.entity.Consumer;
import com.sole.ray.distributed.tx.consumer.dao.ConsumerDao;
import com.sole.ray.distributed.tx.consumer.mq.MQTransactionProducer;
import com.sole.ray.distributed.tx.consumer.param.Business;
import com.sole.ray.distributed.tx.consumer.service.ConsumerService;
import com.sole.ray.distributed.tx.provider.config.ProducerConstant;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Consumer)表服务实现类
 *
 * @author makejava
 * @since 2021-03-01 11:26:00
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private MQTransactionProducer producer;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Consumer queryById(Integer id) {
        return this.consumerDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Consumer> queryAllByLimit(int offset, int limit) {
        return this.consumerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public Consumer insert(Consumer consumer) {
        this.consumerDao.insert(consumer);
        return consumer;
    }

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public Consumer update(Consumer consumer) {
        this.consumerDao.update(consumer);
        return this.queryById(consumer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        return this.consumerDao.deleteById(id) > 0;
    }

    @Override
    public void doBusiness(Business business){
        try {
            producer.send(JSON.toJSONString(business), MQProduerConstant.MQ_PRODUCER_TOPIC);
        } catch (MQClientException e) {
            throw new RuntimeException(e.getErrorMessage());
        }
    }

    @Transactional
    @Override
    public void addConsumer(Consumer consumer,String transactionId) {
        consumerDao.insert(consumer);
    }
}