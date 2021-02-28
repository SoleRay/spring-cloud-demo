package com.sole.ray.distributed.lock.controller;

import com.google.common.base.Stopwatch;
import com.sole.ray.distributed.lock.service.StorageService;
import com.sole.ray.internal.common.anno.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StopWatch;
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
//    @Qualifier("redLockStorageServiceImpl")
    @Qualifier("luaNoLockStorageServiceImpl")
    private StorageService storageService;


    @PostMapping("/decrease")
    public void decreaseStorage(int num) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        storageService.decreaseStorage(num);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeNanos());
    }
}
