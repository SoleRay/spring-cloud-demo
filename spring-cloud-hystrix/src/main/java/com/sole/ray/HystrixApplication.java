package com.sole.ray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 说明
 *      此版本，禁用Feign自带的Hystrix。只把Fegin当RestTemplate使用
 *      熔断器单独用@HystrixCommand来实现
 * 原因
 *      单独使用@HystrixCommand，其灵活性非常大。
 *      可以为每个方法单独设置Hystrix属性，指定异常忽略等。
 */

@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }

}
