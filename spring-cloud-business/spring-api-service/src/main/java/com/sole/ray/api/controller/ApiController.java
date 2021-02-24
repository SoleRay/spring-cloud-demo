package com.sole.ray.api.controller;

import com.sole.ray.api.bean.ApiObject;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseResult
@RequestMapping("/api")
public class ApiController {

    @Value("${server.port}")
    private String port;

    @PostMapping("/object-port")
    public ApiObject objectPort() {
        return new ApiObject(port,"api-service");
    }

    @PostMapping("/string-port")
    public String stringPort() {
        return "api-service:"+port;
    }

    @PostMapping("/int-port")
    public int intPort() {
        return Integer.parseInt(port);
    }

    @PostMapping("/integer-port")
    public Integer integerPort() {
        return Integer.parseInt(port);
    }

    @PostMapping("/result-port")
    public Result resultPort() {
        return Result.success(port);
    }
}
