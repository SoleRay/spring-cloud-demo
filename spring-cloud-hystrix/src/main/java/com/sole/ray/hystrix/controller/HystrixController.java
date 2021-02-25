package com.sole.ray.hystrix.controller;

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
@RequestMapping("/hystrix")
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HystrixFeignOrderService hystrixFeignOrderService;

    @PostMapping("/call-by-rest")
    public Object callByRestTemplate(){
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/get",null, Result.class);
        return responseEntity.getBody();
    }

    @PostMapping("/call-by-feign")
    public Object callByOpenFeign(){
        Result result = hystrixFeignOrderService.getOrder();
        return result.getData();
    }
}
