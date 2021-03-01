package com.sole.ray.distributed.tx.provider.controller;

import com.sole.ray.distributed.tx.provider.entity.Provider;
import com.sole.ray.distributed.tx.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Provider)表控制层
 *
 * @author SoleRay
 * @since 2021-03-01 11:35:42
 */
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


    @GetMapping("/add")
    public void addProvider(Provider provider) {
        providerService.insert(provider);
    }

}