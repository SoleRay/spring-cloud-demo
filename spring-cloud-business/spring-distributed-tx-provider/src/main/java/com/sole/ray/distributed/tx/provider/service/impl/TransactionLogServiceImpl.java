package com.sole.ray.distributed.tx.provider.service.impl;

import com.sole.ray.distributed.tx.provider.entity.TransactionLog;
import com.sole.ray.distributed.tx.provider.dao.TransactionLogDao;
import com.sole.ray.distributed.tx.provider.service.TransactionLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TransactionLog)表服务实现类
 *
 * @author SoleRay
 * @since 2021-03-03 21:38:44
 */
@Service("transactionLogService")
public class TransactionLogServiceImpl implements TransactionLogService {
    @Resource
    private TransactionLogDao transactionLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TransactionLog queryById(String id) {
        return this.transactionLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TransactionLog> queryAllByLimit(int offset, int limit) {
        return this.transactionLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param transactionLog 实例对象
     * @return 实例对象
     */
    @Override
    public TransactionLog insert(TransactionLog transactionLog) {
        this.transactionLogDao.insert(transactionLog);
        return transactionLog;
    }

    /**
     * 修改数据
     *
     * @param transactionLog 实例对象
     * @return 实例对象
     */
    @Override
    public TransactionLog update(TransactionLog transactionLog) {
        this.transactionLogDao.update(transactionLog);
        return this.queryById(transactionLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.transactionLogDao.deleteById(id) > 0;
    }

    @Override
    public int selectById(String id) {
        return this.transactionLogDao.selectById(id);
    }
}