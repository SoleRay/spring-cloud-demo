package com.sole.ray.hystrix.service;

import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.bean.result.ResultCode;
import com.sole.ray.internal.common.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HystrixFeignOrderServiceFallback implements HystrixFeignOrderService{

    @Override
    public Result getOrder() {
        log.error("get order error");
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    @Override
    public Result addOrder(Order order) {
        log.error("add order error");
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    @Override
    public Result modifyOrder(Order order) {
        log.error("modify order error");
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    @Override
    public Result delOrder(int id) {
        log.error("del order error");
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }
}
