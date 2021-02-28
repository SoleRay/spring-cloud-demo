package com.sole.ray.distributed.lock.controller;

import com.sole.ray.distributed.lock.service.StorageService;
import com.sole.ray.internal.common.anno.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ResponseResult
@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
//    @Qualifier("RLockStorageServiceImpl")
//    @Qualifier("jucStorageServiceImpl")
    @Qualifier("redLockStorageServiceImpl")
    private StorageService storageService;


    @PostMapping("/decrease")
    public void decreaseStorage(int num) {
        storageService.decreaseStorage(num);
    }
}
