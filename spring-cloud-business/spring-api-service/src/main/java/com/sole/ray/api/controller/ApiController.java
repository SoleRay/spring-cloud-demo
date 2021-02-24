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

    @GetMapping("/object-port")
    public ApiObject objectPort() {
        return new ApiObject(port,"api-service");
    }

    @GetMapping("/string-port")
    public String stringPort() {
        return "api-service:"+port;
    }

    @GetMapping("/int-port")
    public int intPort() {
        return Integer.parseInt(port);
    }

    @GetMapping("/integer-port")
    public Integer integerPort() {
        return Integer.parseInt(port);
    }
}
