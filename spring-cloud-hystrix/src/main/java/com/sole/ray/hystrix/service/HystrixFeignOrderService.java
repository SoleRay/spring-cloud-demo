package com.sole.ray.hystrix.service;

import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-service")
@RequestMapping("/order")
public interface HystrixFeignOrderService {

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    Result getOrder();

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    Result addOrder();
}
