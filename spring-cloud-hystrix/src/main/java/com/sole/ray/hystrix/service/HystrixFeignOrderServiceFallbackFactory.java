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
                return getResult(e);
            }

            @Override
            public Result addOrder(Order order) {
                return getResult(e);
            }

            @Override
            public Result modifyOrder(Order order) {
                return getResult(e);
            }

            @Override
            public Result delOrder(int id) {
                return getResult(e);
            }

            private Result getResult(Throwable e) {
                log.error(e.getMessage());
                return Result.failure(ResultCode.HYSTRIX_ERROR);
            }
        };
    }
}
