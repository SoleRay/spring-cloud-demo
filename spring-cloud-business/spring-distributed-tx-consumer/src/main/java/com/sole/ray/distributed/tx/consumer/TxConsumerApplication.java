package com.sole.ray.distributed.tx.consumer;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDistributedTransaction
@MapperScan(basePackages="com.sole.ray.distributed.tx.consumer.dao")
@SpringBootApplication(scanBasePackages = {"com.sole.ray.distributed.tx.consumer","com.sole.ray.internal.common"})
public class TxConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxConsumerApplication.class, args);
    }

}
