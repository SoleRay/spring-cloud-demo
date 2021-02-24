package com.sole.ray.ribbon.controller;

import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@ResponseResult
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api-service")
    public Object getApiService(){
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://api-service/api/result-port",null, Result.class);
        return responseEntity.getBody().getData();
    }

    @GetMapping("/order-service")
    public Object getOrderService(){
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://order-service/order/add",null, Result.class);
        return responseEntity.getBody();
    }
}
