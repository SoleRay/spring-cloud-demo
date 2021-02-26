package com.sole.ray.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 注意事项
 *    1.不建议在类上标记@RequestMapping
 *      当@FeignClient不使用fallback的时候，是可以这样做的
 *      但使用fallback以后，这样就会报错。
 *      建议就把@RequestMapping标记在对应的方法上。
 */

@Primary
@FeignClient(value = "order-service", fallbackFactory = HystrixFeignOrderServiceFallbackFactory.class)
//@FeignClient(value = "order-service", fallback = HystrixFeignOrderServiceFallback.class)
public interface HystrixFeignOrderService {


    @RequestMapping(value = "/order/get", method = RequestMethod.POST)
    Result getOrder();

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    Result addOrder(Order order);

    @RequestMapping(value = "/order/modify", method = RequestMethod.POST)
    Result modifyOrder(Order order);

    @RequestMapping(value = "/order/del", method = RequestMethod.POST)
    Result delOrder(int id);
}
