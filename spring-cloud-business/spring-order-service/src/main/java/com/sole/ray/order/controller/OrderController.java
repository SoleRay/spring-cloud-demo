package com.sole.ray.order.controller;

import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@ResponseResult
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @PostMapping("/get")
    public Order getOrder() {
        return new Order(1,Long.parseLong(port));
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody Order order) {
        System.out.println(order);
    }

    @GetMapping("/api-test")
    public Object apiTest() {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://api-service/api/result-port",null, Result.class);
        return responseEntity.getBody().getData();
    }
}
