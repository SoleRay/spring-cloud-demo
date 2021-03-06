package com.sole.ray.order.controller;

import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.order.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@ResponseResult
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/add")
    public void addOrder(Order order) {
        System.out.println(order);
    }

    @GetMapping("/get")
    public Order getOrder() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order(2,255);
    }

}