package com.sole.ray.order.controller;

import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ResponseResult
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/api-test")
    public Object apiTest() {
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://api-service/api/result-port",null, Result.class);
        return responseEntity.getBody().getData();
    }
}
