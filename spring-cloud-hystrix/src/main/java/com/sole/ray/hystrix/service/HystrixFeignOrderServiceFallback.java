package com.sole.ray.hystrix.service;

import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.stereotype.Service;

@Service
public class HystrixFeignOrderServiceFallback {

    public Result getOrder(){
        return null;
    }
}
