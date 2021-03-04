package com.sole.ray.distributed.tx.provider.config.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:properties/mq_consumer.properties")
public class MQConsumerProperties {

    @Value("${mq.namesrv.host}")
    private String namesrvHost;

    @Value("${mq.namesrv.port}")
    private int namesrvPort;

    @Value("${mq.max_reconsume_times}")
    private int maxReconsumeTimes;

    public String getNamesrvAddr(){
        return namesrvHost + ":" + namesrvPort;
    }
}
