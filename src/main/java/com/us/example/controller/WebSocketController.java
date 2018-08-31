package com.us.example.controller;


import com.us.example.bean.Message;
import com.us.example.bean.Response;
import com.us.example.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
 * @author: 通天晓
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
    private KafkaService kafkaService;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/listen")
    public String listen() {
        return "timer";
    }

    @RequestMapping(value = "/ws")
    public String ws() {
        return "ws";
    }

    @RequestMapping(value = "/chat")
    public String chat() {
        return "chat";
    }

    @MessageMapping("/welcome")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @Scheduled(cron = "0/1 * * * * ?") // 模拟kafka定时一直向前端发数据，每秒一条，可以发json
    public void say() throws Exception {
        messagingTemplate.convertAndSend("/topic/getResponse", new Response("当前时间 = " + dateFormat.format(new Date())));
    }

    @MessageMapping("/kafka")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @KafkaListener(topics = {"${spring.kafka.template.default-topic}"}, groupId = "bici-qt-data-test")
    // 模拟kafka定时一直向前端发数据，每秒一条，可以发json
    public void kafka(String content) throws Exception {
        System.out.println(content);
        messagingTemplate.convertAndSend("/topic/kafka", new Response("kafka：  "+content));
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
            messagingTemplate.convertAndSendToUser("user",
                    "/queue/notifications", msg);
        } else {
            messagingTemplate.convertAndSendToUser("admin",
                    "/queue/notifications", msg);
        }
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void sendkafka() {
        log.info("正在发送kafka");
        kafkaService.produce(dateFormat.format(new Date()));
    }

}
