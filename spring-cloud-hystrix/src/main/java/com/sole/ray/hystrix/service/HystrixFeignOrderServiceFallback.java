package com.sole.ray.hystrix.service;

import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.bean.result.ResultCode;
import com.sole.ray.internal.common.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class HystrixFeignOrderServiceFallback implements HystrixFeignOrderService{

    @Override
    public Result getOrder() {
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    @Override
    public Result addOrder(Order order) {
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }
}
