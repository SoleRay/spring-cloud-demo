package com.sole.ray.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sole.ray.hystrix.service.HystrixFeignOrderService;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.bean.result.ResultCode;
import com.sole.ray.internal.common.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ResponseResult
@RestController
@RequestMapping("/hystrix-order")
public class HystrixOrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HystrixFeignOrderService hystrixFeignOrderService;

//    @HystrixCommand(fallbackMethod = "sendFail", commandProperties = {
//            @HystrixProperty(name = "fallback.enabled", value = "false"),
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true")})
    @HystrixCommand(fallbackMethod = "sendFail")
    @PostMapping(value = "/call-by-rest")
    public Result callByRestTemplate(@RequestBody Order order) {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/add", order, Result.class);
        return responseEntity.getBody();
    }

    @PostMapping("/call-by-feign")
    public Result callByOpenFeign() {
        Result result = hystrixFeignOrderService.getOrder();
        return result;
    }

    /**
     * 要求，这个方法的参数列表和返回值，必须和主方法一致。
     */
    private Result sendFail(Order order,Throwable e) {
        log.error(e.getMessage());
        return Result.failure(ResultCode.HYSTRIX_ERROR);
    }
}
