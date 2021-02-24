package com.sole.ray.feign.controller;

import com.sole.ray.feign.entity.Order;
import com.sole.ray.feign.service.FeignOrderService;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResponseResult
@RestController
@RequestMapping("/feign/order")
public class FeignOrderController {

    @Autowired
    private FeignOrderService feignOrderService;

    @PostMapping("add")
    public Object addOrder(){
        Result result = feignOrderService.addOrder();
        return result.getData();
    }
}
