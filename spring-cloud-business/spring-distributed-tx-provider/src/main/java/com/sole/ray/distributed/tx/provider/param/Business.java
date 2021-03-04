package com.sole.ray.distributed.tx.provider.param;

import com.sole.ray.distributed.tx.provider.entity.Provider;
import lombok.Data;

@Data
public class Business {

    private Consumer consumer;

    private Provider provider;
}
