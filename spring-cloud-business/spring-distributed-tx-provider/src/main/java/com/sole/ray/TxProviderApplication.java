package com.sole.ray;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.sole.ray")
@SpringBootApplication
public class TxProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxProviderApplication.class, args);
    }

}
