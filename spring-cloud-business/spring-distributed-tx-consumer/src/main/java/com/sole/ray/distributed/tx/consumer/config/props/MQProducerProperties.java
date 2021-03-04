package com.sole.ray.distributed.tx.consumer.config.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:properties/mq_producer.properties")
public class MQProducerProperties {

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
