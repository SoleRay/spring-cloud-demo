package com.sole.ray.distributed.lock.service.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class JucStorageServiceImpl extends StorageServiceImpl{

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void decreaseStorage(int num) {
        try{
            lock.lock();
            super.decreaseStorage(num);
        }finally {
            lock.unlock();
        }


    }
}
