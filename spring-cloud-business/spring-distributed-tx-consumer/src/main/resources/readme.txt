关于两个项目的关系：
   假设有两个服务，A服务和B服务。其中A服务的t()方法将调用A服务的a()方法和B服务的b()方法来完成一组分布式事务。
   那么

   spring-distributed-tx-consumer: 相当于A服务
   spring-distributed-tx-provider: 相当于B服务

但是对于MQ来说
   spring-distributed-tx-consumer: 消息的生产者
   spring-distributed-tx-provider: 消息的消费者

所以
    spring-distributed-tx-consumer中嵌入了MQProduer
    spring-distributed-tx-provider中嵌入了MQConsumer

只能说之前项目的名字取得不是很恰当