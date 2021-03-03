package com.sole.ray.distributed.tx.provider.service;

import com.sole.ray.distributed.tx.provider.entity.Provider;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface ProviderTccService {

    @TwoPhaseBusinessAction(name = "addProviderTccAction" , commitMethod = "addProviderCommit" ,rollbackMethod = "addProviderRollback")
    void addProvider(Provider provider);

    boolean addProviderCommit(BusinessActionContext businessActionContext);

    boolean addProviderRollback(BusinessActionContext businessActionContext);

}
