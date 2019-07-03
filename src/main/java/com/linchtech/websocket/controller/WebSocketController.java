package com.linchtech.websocket.controller;


import com.linchtech.websocket.bean.Message;
import com.linchtech.websocket.bean.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * comment
 *
 * @author: linch
 * @review:
 * @date: 2018-08-29 17:26
 * @version: 1.0
 */
@CrossOrigin
@Controller
@Slf4j
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/timer")
    public String listen() {
        return "timer";
    }

    @RequestMapping(value = "/ws")
    public String ws() {
        return "ws";
    }

    @RequestMapping(value = "/chat")
    public String chat() {
        // 聊天需要去掉security的注释和包引用，并登陆user和admin
        return "chat";
    }

    @MessageMapping("/welcome")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @Scheduled(cron = "0/1 * * * * ?") // 模拟kafka定时一直向前端发数据，每秒一条，可以发json
    public void say() throws Exception {
        // 向前端页面发送时间,前端页面ws.html监听/topic/getResponse
        messagingTemplate.convertAndSend("/topic/getResponse", new Response("当前时间 === " + dateFormat.format(new Date())));
    }

    @MessageMapping("/kafka")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @RabbitListener(queues = "ws-test")
    // 模拟MQ定时一直向前端发数据，每秒一条，可以发json
    public void kafka(String content) throws Exception {
        System.out.println(content);
        // 向前端页面发送数据,前端timer.html里面监听/topic/kafka
        messagingTemplate.convertAndSend("/topic/kafka", new Response("MQ：  " + content));
    }

    @MessageMapping("/chat")// 两个页面互相发送消息，聊天
    //在springmvc 中可以直接获得principal,principal 中包含当前用户的信息
    public void handleChat(Principal principal, Message message) {
        String msg = "<span style=color:red>" + principal.getName() + " " + dateFormat.format(new Date()) + ":</span> "
                + " '<span>" + message.getName() + "'</span>";
        /**
         * 此处是一段硬编码。admin 则发送给 user 如果发送人是user 就发送给 admin。
         * 通过当前用户,然后查找消息,如果查找到未读消息,则发送给当前用户。
         */
        if (principal.getName().equals("admin")) {
            //通过convertAndSendToUser 向用户发送信息,
            // 第一个参数是接收消息的用户,第二个参数是浏览器订阅的地址,第三个参数是消息本身
            // messagingTemplate.convertAndSendToUser("user",
            //         "/queue/notifications", msg);
        } else {
            // messagingTemplate.convertAndSendToUser("admin",
            //         "/queue/notifications", msg);
        }
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void sendkafka() {
        log.info("正在发送rabbitMQ消息,当前时间:{}", dateFormat.format(new Date()));
        // kafkaService.produce(dateFormat.format(new Date()));
        rabbitMessagingTemplate.convertAndSend("ws-test", dateFormat.format(new Date()));
    }

}
