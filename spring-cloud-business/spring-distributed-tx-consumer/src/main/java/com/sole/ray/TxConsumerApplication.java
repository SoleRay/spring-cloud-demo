package com.sole.ray;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.sole.ray")
@EnableFeignClients
@SpringBootApplication
public class TxConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxConsumerApplication.class, args);
    }

}
