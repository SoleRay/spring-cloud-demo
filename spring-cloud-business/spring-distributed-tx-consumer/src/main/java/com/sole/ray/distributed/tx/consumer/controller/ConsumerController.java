package com.sole.ray.distributed.tx.consumer.controller;

import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.sole.ray.distributed.tx.consumer.entity.Consumer;
import com.sole.ray.distributed.tx.consumer.param.Business;
import com.sole.ray.distributed.tx.consumer.service.ConsumerService;
import com.sole.ray.internal.common.anno.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get")
    public Consumer get(Integer id) {
        return this.consumerService.queryById(id);
    }

    @PostMapping("/doBusiness")
    public void doBusiness(@RequestBody Business business) {
        consumerService.doBusiness(business);
    }
}