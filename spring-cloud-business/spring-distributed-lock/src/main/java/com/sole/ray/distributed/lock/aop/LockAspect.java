package com.sole.ray.distributed.lock.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Order(0)
@Configuration
public class LockAspect {

    private static final String LOCK_KEY = "box:lock";

    @Autowired
    protected RedissonClient redissonClient;

    @Pointcut(value = "execution(public * com.sole.ray..*(..)) && @annotation(com.sole.ray.distributed.lock.anno.RLock)")
    public void rlockPointcut() {
    }

    @Around("rlockPointcut()")
    public void rLock(ProceedingJoinPoint pjp) {
        RLock lock = redissonClient.getLock(LOCK_KEY);
        try {
            lock.lock();
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
