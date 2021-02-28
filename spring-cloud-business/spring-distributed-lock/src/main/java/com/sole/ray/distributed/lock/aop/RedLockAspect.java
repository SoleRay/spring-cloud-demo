package com.sole.ray.distributed.lock.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Aspect
@Order(0)
@Configuration
public class RedLockAspect {

    private static final String LOCK_KEY = "box:lock";

    @Autowired
    @Qualifier("redissonRed1")
    protected RedissonClient redissonRed1;

    @Autowired
    @Qualifier("redissonRed2")
    protected RedissonClient redissonRed2;

    @Autowired
    @Qualifier("redissonRed3")
    protected RedissonClient redissonRed3;

    @Pointcut(value = "execution(public * com.sole.ray..*(..)) && @annotation(com.sole.ray.distributed.lock.anno.RedLock)")
    public void redlockPointcut() {
    }

    @Around("redlockPointcut()")
    public void rLock(ProceedingJoinPoint pjp) {
        RLock rLock1 = redissonRed1.getLock(LOCK_KEY);
        RLock rLock2 = redissonRed2.getLock(LOCK_KEY);
        RLock rLock3 = redissonRed3.getLock(LOCK_KEY);

        RedissonRedLock redLock = new RedissonRedLock(rLock1, rLock2, rLock3);
        try {
            redLock.lock();
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            redLock.unlock();
        }
    }

}
