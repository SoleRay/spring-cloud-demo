package com.sole.ray.distributed.tx.consumer.service.impl;

import com.sole.ray.distributed.tx.consumer.dao.ConsumerDao;
import com.sole.ray.distributed.tx.consumer.feign.FeignProviderService;
import com.sole.ray.distributed.tx.consumer.param.Business;
import com.sole.ray.distributed.tx.consumer.service.ConsumerTccService;
import com.sole.ray.internal.common.bean.result.Result;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerTccServiceImpl implements ConsumerTccService {

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private FeignProviderService feignProviderService;

    /**
     * Seata的AT模式和LCN一样：
     *      调用服务提供者的方法，如果出现异常，必须对其进行处理，并在当前方法抛出异常，才能保证事务的一致性。
     *
     */
//

    @Transactional
    @Override
    public void doBusinessPrepare(Business business) {
        consumerDao.insert(business.getConsumer());
        Result result = feignProviderService.addProvider(business.getProvider());
        if(!result.isSuccess()){
            throw new RuntimeException(result.getMessage());
        }

        int x = 1/0;
    }

    @Override
    public boolean doBusinessCommit(BusinessActionContext businessActionContext) {
        System.out.println("consumer seata tcc: confirm");
        return true;
    }

    @Override
    public boolean doBusinessRollback(BusinessActionContext businessActionContext) {
        System.out.println("consumer seata tcc: rollback");
        return true;
    }
}
