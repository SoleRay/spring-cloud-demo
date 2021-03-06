package com.sole.ray.distributed.tx.provider.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.distributed.tx.provider.dao.ProviderDao;
import com.sole.ray.distributed.tx.provider.service.ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Provider)表服务实现类
 *
 * @author SoleRay
 * @since 2021-03-01 11:35:42
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderDao providerDao;

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
    @LcnTransaction
    @Override
    public Provider insert(Provider provider) {
        this.providerDao.insert(provider);
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
}