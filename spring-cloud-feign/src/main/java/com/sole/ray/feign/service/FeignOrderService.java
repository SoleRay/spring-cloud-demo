package com.sole.ray.feign.service;

import com.sole.ray.feign.entity.Order;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-service")
@RequestMapping("/order")
public interface FeignOrderService {

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    Result<Order> getOrder();

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    Result addOrder(Order order);
}
