package com.sole.ray.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.bean.result.ResultCode;
import com.sole.ray.internal.common.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@ResponseResult
@RestController
@RequestMapping("/hystrix-order")
public class HystrixOrderController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getOrderFailed")
    @PostMapping("/get")
    public Object getOrder() {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/get", null, Result.class);
        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod = "addOrderFailed", commandProperties = {
            @HystrixProperty(name = "fallback.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true")})
    @PostMapping("/add")
    public Object addOrder(@RequestBody Order order) {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/add", order, Result.class);
        return responseEntity.getBody();
    }



    /**
     * 要求，这个方法的参数列表和返回值，必须和主方法一致。
     */
    private Result getOrderFailed() {
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }

    private Result addOrderFailed(Order order,Throwable e) {

        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }
}
