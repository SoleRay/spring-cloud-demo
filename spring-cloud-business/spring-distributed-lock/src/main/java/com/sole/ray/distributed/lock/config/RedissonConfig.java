package com.sole.ray.distributed.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RedissonConfig {

    private static final String REDIS_PROTOCOL_PREFIX = "redis://";
    private static final String REDISS_PROTOCOL_PREFIX = "rediss://";

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 因为红锁配置了3个客户端，导致RedissonAutoConfiguration里的RedissonClient配置失效。
     * 必须在这里手动将RedissonAutoConfiguration里创建sentinel-RedissonClient的代码copy过来
     *
     */
    @Primary
    @Bean
    public RedissonClient redissonClient(){
        int timeout = getTimeout();

        Method nodesMethod = ReflectionUtils.findMethod(RedisProperties.Sentinel.class, "getNodes");
        Object nodesValue = ReflectionUtils.invokeMethod(nodesMethod, redisProperties.getSentinel());

        String[] nodes;
        if (nodesValue instanceof String) {
            nodes = convert(Arrays.asList(((String)nodesValue).split(",")));
        } else {
            nodes = convert((List<String>)nodesValue);
        }

        Config config = new Config();
        config.useSentinelServers()
                .setMasterName(redisProperties.getSentinel().getMaster())
                .addSentinelAddress(nodes)
                .setDatabase(redisProperties.getDatabase())
                .setConnectTimeout(timeout)
                .setPassword(redisProperties.getPassword());
        return Redisson.create(config);
    }

    @Bean
    public RedissonClient redissonRed1(){
        return createRedissonRed("192.168.0.10",16000);
    }

    @Bean
    public RedissonClient redissonRed2(){
        return createRedissonRed("192.168.0.11",16000);
    }
    @Bean
    public RedissonClient redissonRed3(){
        return createRedissonRed("192.168.0.12",16000);
    }

    private RedissonClient createRedissonRed(String host,int port) {
        int timeout = getTimeout();

        Config config = new Config();
        String prefix = REDIS_PROTOCOL_PREFIX;
        Method method = ReflectionUtils.findMethod(RedisProperties.class, "isSsl");
        if (method != null && (Boolean)ReflectionUtils.invokeMethod(method, redisProperties)) {
            prefix = REDISS_PROTOCOL_PREFIX;
        }

        config.useSingleServer()
                .setAddress(prefix + host + ":" + port)
                .setConnectTimeout(timeout)
                .setDatabase(redisProperties.getDatabase())
                .setPassword(redisProperties.getPassword());
        return Redisson.create(config);
    }

    private int getTimeout() {
        Method timeoutMethod = ReflectionUtils.findMethod(RedisProperties.class, "getTimeout");
        Object timeoutValue = ReflectionUtils.invokeMethod(timeoutMethod, redisProperties);

        int timeout;
        if(null == timeoutValue){
            timeout = 10000;
        }else if (!(timeoutValue instanceof Integer)) {
            Method millisMethod = ReflectionUtils.findMethod(timeoutValue.getClass(), "toMillis");
            timeout = ((Long) ReflectionUtils.invokeMethod(millisMethod, timeoutValue)).intValue();
        } else {
            timeout = (Integer)timeoutValue;
        }
        return timeout;
    }

    private String[] convert(List<String> nodesObject) {
        List<String> nodes = new ArrayList<String>(nodesObject.size());
        for (String node : nodesObject) {
            if (!node.startsWith(REDIS_PROTOCOL_PREFIX) && !node.startsWith(REDISS_PROTOCOL_PREFIX)) {
                nodes.add(REDIS_PROTOCOL_PREFIX + node);
            } else {
                nodes.add(node);
            }
        }
        return nodes.toArray(new String[nodes.size()]);
    }
}
