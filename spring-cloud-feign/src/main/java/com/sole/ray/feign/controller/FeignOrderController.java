package com.sole.ray.feign.controller;

import com.sole.ray.feign.service.FeignOrderService;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResponseResult
@RestController
@RequestMapping("feign/order")
public class FeignOrderController {

    @Autowired
    private FeignOrderService feignOrderService;

    @PostMapping("add")
    public void addOrder() {
        Result result = feignOrderService.addOrder(new Order(1, 1000));
    }

    @GetMapping("get")
    public Order getOrder() {
        Result<Order> result = feignOrderService.getOrder();
        return result.getData();
    }
}
