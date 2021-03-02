package com.sole.ray.distributed.tx.consumer.controller;

import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.sole.ray.distributed.tx.consumer.entity.Consumer;
import com.sole.ray.distributed.tx.consumer.feign.FeignProviderService;
import com.sole.ray.distributed.tx.consumer.param.Business;
import com.sole.ray.distributed.tx.consumer.service.ConsumerService;
import com.sole.ray.internal.common.anno.ResponseResult;
import com.sole.ray.internal.common.bean.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Consumer)表控制层
 *
 * @author makejava
 * @since 2021-03-01 11:26:00
 */
@ResponseResult
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private FeignProviderService feignProviderService;

    @GetMapping("/get")
    public Consumer get(Integer id) {
        return this.consumerService.queryById(id);
    }

    @Transactional
    @TccTransaction
    @PostMapping("/doBusiness")
    public void doBusiness(@RequestBody Business business) {
        consumerService.insert(business.getConsumer());
        feignProviderService.addProvider(business.getProvider());
    }

    public void confirmDoBusiness(@RequestBody Business business){
        System.out.println("OK");
    }

    public void cancelDoBusiness(@RequestBody Business business){
        System.out.println("cancelDoBusiness");
        consumerService.deleteById(business.getConsumer().getId());
    }
}