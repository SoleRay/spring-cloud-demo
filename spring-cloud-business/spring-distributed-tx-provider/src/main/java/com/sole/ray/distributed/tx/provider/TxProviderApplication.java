package com.sole.ray.distributed.tx.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.sole.ray.distributed.tx.provider.dao")
@SpringBootApplication(scanBasePackages = {"com.sole.ray.distributed.tx.provider","com.sole.ray.internal.common"})
public class TxProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxProviderApplication.class, args);
    }

}
