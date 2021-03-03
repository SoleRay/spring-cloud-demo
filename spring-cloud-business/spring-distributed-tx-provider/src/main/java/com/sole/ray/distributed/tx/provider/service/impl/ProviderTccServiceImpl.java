package com.sole.ray.distributed.tx.provider.service.impl;

import com.sole.ray.distributed.tx.provider.dao.ProviderDao;
import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.distributed.tx.provider.service.ProviderTccService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProviderTccServiceImpl implements ProviderTccService {

    @Autowired
    private ProviderDao providerDao;

    @Transactional
    @Override
    public void addProvider(Provider provider) {
        providerDao.insert(provider);
    }

    @Override
    public boolean addProviderCommit(BusinessActionContext businessActionContext) {
        System.out.println("consumer seata tcc: confirm");
        return true;
    }

    @Override
    public boolean addProviderRollback(BusinessActionContext businessActionContext) {
        System.out.println("consumer seata tcc: rollback");
        return true;
    }
}
