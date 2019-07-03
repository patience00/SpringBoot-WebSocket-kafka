# SpringBoot-WebSocket-kafka
这个demo是基于STOMP协议的，对前后分离的项目支持不是很好
1、打开localhost:8080/ws 页面可以显示后端的定时任务刷新的时间
2、打开localhost:8080/timer 页面可以显示kafka发送的消息
3、项目跑起来需要本地有自己搭建的rabbitMQ服务,可以自行更换MQ
4,如果要站内聊天,需要SpringSecurity的包
