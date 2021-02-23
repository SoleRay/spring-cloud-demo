package com.sole.ray.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test() {
        System.out.println("api-test");
        return "api-service:" + port;
    }
}
