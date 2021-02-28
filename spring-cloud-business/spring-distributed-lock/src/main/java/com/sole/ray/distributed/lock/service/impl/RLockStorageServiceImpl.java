package com.sole.ray.distributed.lock.service.impl;

import com.sole.ray.distributed.lock.anno.RLock;
import org.springframework.stereotype.Service;

/**
 * 这样写只是为了对各种锁分类用以区分
 * 实际上，直接在StorageServiceImpl的decreaseStorage上打注解就可以了
 */
@Service
public class RLockStorageServiceImpl extends StorageServiceImpl{

    @RLock
    @Override
    public void decreaseStorage(int num) {
        super.decreaseStorage(num);
    }
}
