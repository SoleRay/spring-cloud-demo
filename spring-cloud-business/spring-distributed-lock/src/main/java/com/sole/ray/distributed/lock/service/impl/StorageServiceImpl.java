package com.sole.ray.distributed.lock.service.impl;

import com.sole.ray.distributed.lock.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Primary
@Service
public class StorageServiceImpl implements StorageService {

    private static final String STORAGE_KEY = "box:num";

    private final AtomicInteger count = new AtomicInteger();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void decreaseStorage(int num) {
        int i = count.incrementAndGet();

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
}
