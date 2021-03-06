package com.sole.ray.order.controller;

import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.order.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseResult
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/add")
    public void addOrder(Order order) {
        System.out.println(order);
    }

    @PostMapping("/get")
    public Order stringPort() {
        return new Order(2,255);
    }

}