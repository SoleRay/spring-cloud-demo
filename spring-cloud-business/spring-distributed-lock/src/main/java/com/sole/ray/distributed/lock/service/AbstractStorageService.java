package com.sole.ray.distributed.lock.service;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractStorageService<Lock> implements StorageService {

    private final AtomicInteger count = new AtomicInteger();

    private static final String STORAGE_KEY = "box:num";

    @Autowired
    protected RedissonClient redissonClient;

    @Autowired
    protected RedisTemplate redisTemplate;

    @Override
    public void decreaseStorage(int num) {

        Lock lock = null;
        try {
            lock = lock();
            doDecreaseStorage(num);
        } finally {
            if (lock != null) {
                unlock(lock);
            }
        }
    }

    private void doDecreaseStorage(int num) {
        int i = count.incrementAndGet() % 20;

        Object value = redisTemplate.opsForValue().get(STORAGE_KEY);
        if (value == null) {
            throw new RuntimeException("key=" + STORAGE_KEY + ",redis中不存在");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int currentStorageNum = Integer.parseInt(value.toString());
        if (currentStorageNum <= 0) {
            System.out.println("第" + i + "个线程，currentStorageNum=" + currentStorageNum + ",无法继续出售");
            return;
        }

        int newStorageNum = currentStorageNum - num;
        redisTemplate.opsForValue().set(STORAGE_KEY, String.valueOf(newStorageNum));
        System.out.println("第" + i + "个线程，currentStorageNum=" + currentStorageNum + ",newStorageNum=" + newStorageNum);
    }

    protected Lock lock() {
        return null;
    }

    protected void unlock(Lock lock) {
    }
}
