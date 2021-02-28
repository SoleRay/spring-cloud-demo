package com.sole.ray.distributed.lock.service.impl;

import com.sole.ray.distributed.lock.anno.RedLock;
import org.springframework.stereotype.Service;

@Service
public class RedLockStorageServiceImpl extends StorageServiceImpl {

    @RedLock
    @Override
    public void decreaseStorage(int num) {
        super.decreaseStorage(num);
    }
}
