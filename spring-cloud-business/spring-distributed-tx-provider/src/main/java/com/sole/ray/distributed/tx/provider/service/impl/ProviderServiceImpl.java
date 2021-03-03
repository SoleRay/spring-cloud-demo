package com.sole.ray.distributed.tx.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.sole.ray.distributed.tx.provider.config.ProducerConstant;
import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.distributed.tx.provider.dao.ProviderDao;
import com.sole.ray.distributed.tx.provider.mq.TransactionProducer;
import com.sole.ray.distributed.tx.provider.service.ProviderService;
import com.sole.ray.distributed.tx.provider.service.TransactionLogService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Provider)表服务实现类
 *
 * @author SoleRay
 * @since 2021-03-01 11:35:42
 */
@Service
public class ProviderServiceImpl implements ProviderService {


    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private TransactionProducer producer;

    @Autowired
    private TransactionLogService transactionLogService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Provider queryById(Integer id) {
        return this.providerDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Provider> queryAllByLimit(int offset, int limit) {
        return this.providerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param provider 实例对象
     * @return 实例对象
     */

    @Transactional
    @Override
    public Provider insert(Provider provider) {
        this.providerDao.insert(provider);
        return provider;
    }

    public Provider confirmInsert(Provider provider) {
        System.out.println("provider:confirm tcc");
        return provider;
    }

    public Provider cancelInsert(Provider provider) {
        System.out.println("provider:cancel tcc");
        return provider;
    }

    /**
     * 修改数据
     *
     * @param provider 实例对象
     * @return 实例对象
     */
    @Override
    public Provider update(Provider provider) {
        this.providerDao.update(provider);
        return this.queryById(provider.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.providerDao.deleteById(id) > 0;
    }

    @Transactional
    @Override
    public void addProvider(Provider provider){
        providerDao.insert(provider);

        try {
            producer.send(JSON.toJSONString(provider), ProducerConstant.PRODUCE_TOPIC);
        } catch (MQClientException e) {
            throw new RuntimeException(e.getErrorMessage());
        }
    }
}