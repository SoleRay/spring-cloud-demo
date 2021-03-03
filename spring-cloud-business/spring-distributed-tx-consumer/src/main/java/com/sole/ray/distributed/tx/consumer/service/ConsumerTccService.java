package com.sole.ray.distributed.tx.consumer.service;

import com.sole.ray.distributed.tx.consumer.param.Business;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface ConsumerTccService {

    @TwoPhaseBusinessAction(name = "doBusinessTccAction" , commitMethod = "doBusinessCommit" ,rollbackMethod = "doBusinessRollback")
    void doBusinessPrepare(Business business);

    boolean doBusinessCommit(BusinessActionContext businessActionContext);

    boolean doBusinessRollback(BusinessActionContext businessActionContext);
}
