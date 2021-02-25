package com.sole.ray.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sole.ray.hystrix.service.HystrixFeignOrderService;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    @PostMapping("/call-by-rest")
    public Object callByRestTemplate() {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/get", null, Result.class);
        return responseEntity.getBody();
    }

    @PostMapping("/call-by-feign")
    public Object callByOpenFeign() {
        Result result = hystrixFeignOrderService.getOrder();
        return result.getData();
    }

    /**
     * 要求，这个方法的参数列表和返回值，必须和主方法一致。
     */
    private Object sendFail() {
        return null;
    }
}
