package com.sole.ray.distributed.tx.consumer.feign;

import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tx-provider")
public interface FeignProviderService {

    @RequestMapping(value = "/provider/add",method = RequestMethod.POST)
    Result addProvider(@RequestBody Provider provider);
}