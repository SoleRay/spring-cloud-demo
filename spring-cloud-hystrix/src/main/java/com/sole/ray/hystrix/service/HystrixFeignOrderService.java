package com.sole.ray.hystrix.service;

import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 注意事项
 *    1.不建议在类上标记@RequestMapping
 *      当@FeignClient不使用fallback的时候，是可以这样做的
 *      但使用fallback以后，这样就会报错。
 *      建议就把@RequestMapping标记在对应的方法上。
 */


//@FeignClient(value = "order-service", fallback = HystrixFeignOrderServiceFallback.class)
//@RequestMapping("/order")
@FeignClient(value = "order-service", fallbackFactory = HystrixFeignOrderServiceFallbackFactory.class)
public interface HystrixFeignOrderService {

    @RequestMapping(value = "/order/get", method = RequestMethod.POST)
    Result getOrder();

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    Result addOrder(Order order);
}
