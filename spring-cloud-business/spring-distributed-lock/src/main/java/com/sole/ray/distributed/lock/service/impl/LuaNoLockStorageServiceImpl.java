package com.sole.ray.distributed.lock.service.impl;

import com.sole.ray.internal.common.bean.result.Result;
import com.sole.ray.internal.common.util.JsonUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LuaNoLockStorageServiceImpl extends StorageServiceImpl {

    @Override
    public void decreaseStorage(int num) {

        int i = count.incrementAndGet();

        DefaultRedisScript<Result> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Result.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/lua/storage.lua")));

        Object object = redisTemplate.execute(redisScript, Arrays.asList(STORAGE_KEY), num);
        Result result = JsonUtil.convert(object, Result.class);

        if(result.isSuccess()){
            System.out.println("第" + i + "个线程，购买了" + num + "个box，库存还剩余" + result.getData() + "个box");
        }else if (!result.isInternalError()){
            System.out.println("第" + i + "个线程，库存不足,无法继续出售");
        }else {
            throw new RuntimeException(result.getMessage());
        }
    }
}
