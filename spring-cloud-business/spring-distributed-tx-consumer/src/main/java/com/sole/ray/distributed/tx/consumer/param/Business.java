package com.sole.ray.distributed.tx.consumer.param;

import com.sole.ray.distributed.tx.consumer.entity.Consumer;
import com.sole.ray.distributed.tx.provider.entity.Provider;
import lombok.Data;

@Data
public class Business {

    private Consumer consumer;

    private Provider provider;
}
