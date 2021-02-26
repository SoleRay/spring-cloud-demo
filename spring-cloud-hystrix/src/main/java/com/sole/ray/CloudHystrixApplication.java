package com.sole.ray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixApplication.class, args);
    }

}
