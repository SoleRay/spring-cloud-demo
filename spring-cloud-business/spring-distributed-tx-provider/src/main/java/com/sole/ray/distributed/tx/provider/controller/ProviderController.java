package com.sole.ray.distributed.tx.provider.controller;

import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.distributed.tx.provider.service.ProviderService;
import com.sole.ray.internal.common.anno.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Provider)表控制层
 *
 * @author SoleRay
 * @since 2021-03-01 11:35:42
 */
@ResponseResult
@RestController
@RequestMapping("/provider")
public class ProviderController {
    /**
     * 服务对象
     */
    @Autowired
    private ProviderService providerService;

    @GetMapping("/get")
    public Provider getdProvider(Integer id) {
        return this.providerService.queryById(id);
    }


    @Transactional
    @TccTransaction
    @PostMapping("/add")
    public void addProvider(@RequestBody Provider provider) {
        providerService.insert(provider);
    }

    public void confirmInsert(@RequestBody Provider provider){
        System.out.println("OK");
    }

    public void cancelInsert(@RequestBody Provider provider){
        System.out.println("cancelInsert");
    }
}