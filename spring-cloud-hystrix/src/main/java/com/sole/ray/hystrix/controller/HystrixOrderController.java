package com.sole.ray.hystrix.controller;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.sole.ray.hystrix.service.HystrixFeignOrderService;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ResponseResult
@RestController
@RequestMapping("/hystrix-order")
public class HystrixOrderController {

    @Autowired
    private HystrixFeignOrderService hystrixFeignOrderService;

    @PostMapping("/get")
    public Result getOrder() {
        return hystrixFeignOrderService.getOrder();
    }

    @PostMapping("/add")
    public Result addOrder(@RequestBody Order order) {
        return hystrixFeignOrderService.addOrder(order);
    }

    @PostMapping("/modify")
    public Result modifyOrder(@RequestBody Order order) {
        int i = 1/0;
        return hystrixFeignOrderService.modifyOrder(order);
    }

    @PostMapping("/del")
    public Result delOrder(int id) {
        try{
            int i = 1/0;
        }catch (Exception e){
            throw new HystrixBadRequestException(e.getMessage(),e);
        }

        return hystrixFeignOrderService.delOrder(id);
    }
}
