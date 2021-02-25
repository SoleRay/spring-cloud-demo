package com.sole.ray.hystrix.service;

import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.bean.result.ResultCode;
import com.sole.ray.internal.common.entity.Order;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HystrixFeignOrderServiceFallbackFactory implements FallbackFactory<HystrixFeignOrderService> {

    @Override
    public HystrixFeignOrderService create(Throwable e) {
        return new HystrixFeignOrderService() {
            @Override
            public Result getOrder() {
                log.error(e.getMessage());
                return Result.failure(ResultCode.HYSTRIX_ERROR);
            }

            @Override
            public Result addOrder(Order order) {
                log.error(e.getMessage());
                return Result.failure(ResultCode.HYSTRIX_ERROR);
            }
        };
    }
}
