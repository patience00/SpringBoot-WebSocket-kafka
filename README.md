kafka已经替换成了RabbitMQ了 这个demo是基于STOMP协议的，对前后分离的项目支持不是很好

**演示步骤：**

 1、打开localhost:8080/ws 页面可以显示后端的定时任务刷新的时间 

2、打开localhost:8080/timer 页面可以显示MQ发送的消息 

3、项目跑起来需要本地有自己搭建的rabbitMQ服务,有现成的docker镜像可以直接部署 

```
docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
```

4,如果要站内聊天,需要SpringSecurity的包

