package com.sole.ray.api.controller;

import com.sole.ray.api.bean.ApiObject;
import com.sole.ray.internal.common.anno.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseResult
@RequestMapping("/api")
public class ApiController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public ApiObject test() {
        return new ApiObject(port,"api-service");
    }
}
