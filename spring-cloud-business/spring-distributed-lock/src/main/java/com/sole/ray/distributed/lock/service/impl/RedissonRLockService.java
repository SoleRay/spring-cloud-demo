package com.sole.ray.distributed.lock.service.impl;

import com.sole.ray.distributed.lock.service.AbstractStorageService;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("redisson-rlock-service")
public class RedissonRLockService extends AbstractStorageService<RLock> {

    private static final String LOCK_KEY = "box:lock";

    @Override
    protected RLock lock() {
        RLock lock = redissonClient.getLock(LOCK_KEY);
        lock.lock(2000, TimeUnit.MILLISECONDS);
        return lock;
    }

    @Override
    protected void unlock(RLock lock) {
        lock.unlock();
    }
}
