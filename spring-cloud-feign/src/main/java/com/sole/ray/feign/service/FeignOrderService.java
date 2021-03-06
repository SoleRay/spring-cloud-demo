package com.sole.ray.feign.service;

import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("order-service")
@RequestMapping("/order")
public interface FeignOrderService {

    @GetMapping(value = "/get")
    Result<Order> getOrder();

    @PostMapping(value = "/add")
    Result addOrder(Order order);
}
