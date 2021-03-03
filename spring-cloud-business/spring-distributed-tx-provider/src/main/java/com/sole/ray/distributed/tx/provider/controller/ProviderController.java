package com.sole.ray.distributed.tx.provider.controller;

import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.distributed.tx.provider.service.ProviderService;
import com.sole.ray.internal.common.anno.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/add")
    public void addProvider(@RequestBody Provider provider) {
        providerService.addProvider(provider);
    }
}