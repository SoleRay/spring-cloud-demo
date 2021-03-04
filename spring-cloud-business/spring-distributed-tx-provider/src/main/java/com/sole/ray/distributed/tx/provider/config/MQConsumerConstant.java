package com.sole.ray.distributed.tx.provider.config;

public interface MQConsumerConstant {

    String MQ_PRODUCER_TOPIC = "tx_consumer";

    String MQ_CONSUMER_TOPIC = "tx_provider";

    String MQ_CONSUMER_GROUP = "tx_provider_group";

    String DLQ_CONSUMER_GROUP = "tx_provider_dlq_group";

    String DLQ_CONSUMER_TOPIC = "tx_provider_dlq";
}
