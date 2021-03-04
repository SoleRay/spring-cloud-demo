package com.sole.ray.distributed.tx.consumer.service;

import com.sole.ray.distributed.tx.consumer.entity.TransactionLog;

import java.util.List;

/**
 * (TransactionLog)表服务接口
 *
 * @author SoleRay
 * @since 2021-03-03 21:38:44
 */
public interface TransactionLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TransactionLog queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TransactionLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param transactionLog 实例对象
     * @return 实例对象
     */
    TransactionLog insert(TransactionLog transactionLog);

    /**
     * 修改数据
     *
     * @param transactionLog 实例对象
     * @return 实例对象
     */
    TransactionLog update(TransactionLog transactionLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    int selectById(String id);
}