package com.sole.ray.distributed.lock.service.impl;

import com.sole.ray.distributed.lock.service.AbstractStorageService;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service("juc-lock-service")
public class JUCLockService extends AbstractStorageService<ReentrantLock> {

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    protected ReentrantLock lock() {
        lock.lock();
        return lock;
    }

    @Override
    protected void unlock(ReentrantLock lock) {
        this.lock.unlock();
    }
}
