package com.sole.ray.distributed.tx.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTransactionManagerServer
@SpringBootApplication
public class DistributedTxTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedTxTmApplication.class, args);
    }

}
