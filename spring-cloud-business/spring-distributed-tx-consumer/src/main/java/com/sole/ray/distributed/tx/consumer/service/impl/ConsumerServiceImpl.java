package com.sole.ray.distributed.tx.consumer.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.sole.ray.distributed.tx.consumer.entity.Consumer;
import com.sole.ray.distributed.tx.consumer.dao.ConsumerDao;
import com.sole.ray.distributed.tx.consumer.feign.FeignProviderService;
import com.sole.ray.distributed.tx.consumer.param.Business;
import com.sole.ray.distributed.tx.consumer.service.ConsumerService;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    private FeignProviderService feignProviderService;

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

    /**
     * 关于TCC异常回滚机制的说明，以provider和consumer为例
     *    1.consumer本身发生异常
     *      1.1 provider已经执行，无论是否成功，都回滚
     *      1.2 provider尚未执行，不执行。
     *    2.provider发生异常
     *      无论provider处于doBussiness的哪个位置。都必须获取它的结果，根据结果手动抛出异常
     *      若provider发生异常后，doBussiness方法本身不主动抛出异常，则consumerDao.insert()不会回滚。
     *
     *    这和LCN是一样的，可能是因为用的是同一组件的缘故
     */
    @Transactional
    @TccTransaction
    @Override
    public void doBusiness(Business business) {

        consumerDao.insert(business.getConsumer());
        Result result = feignProviderService.addProvider(business.getProvider());
        if(!result.isSuccess()){
            throw new RuntimeException(result.getMessage());
        }
    }

    public void confirmDoBusiness(Business business) {
        System.out.println("consumer:confirm tcc");
    }

    public void cancelDoBusiness(Business business) {
        System.out.println("consumer:cancel tcc");
    }
}