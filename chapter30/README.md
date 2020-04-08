# Sentinel Apache Dubbo Demo

在复杂的生产环境下可能部署着成千上万的 Dubbo 服务实例，流量持续不断地进入，
服务之间进行相互调用。但是分布式系统中可能会因流量激增、系统负载过高、网络延迟等一系列问题，
导致某些服务不可用，如果不进行相应的控制可能导致级联故障，
影响服务的可用性，因此如何对流量进行合理的控制，成为保障服务稳定性的关键。

http://dubbo.apache.org/zh-cn/blog/sentinel-introduction-for-dubbo.html
## Run the demo

For the provider demo `FooProviderBootstrap`, you need to add the following parameters when startup:

```shell
-Djava.net.preferIPv4Stack=true -Dproject.name=dubbo-provider-demo
```

For the consumer demo `FooConsumerBootstrap`, you need to add the following parameters when startup:

```shell
-Djava.net.preferIPv4Stack=true -Dproject.name=dubbo-consumer-demo
```
