package com.linchtech.websocket.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: linch
 * @date: 2018/10/26 11:31
 * @description:
 */
@Configuration
public class TopicRabbitConfig {

    public static final String topic = "fanout.Test";

    @Bean
    FanoutExchange fanoutExchangeLocation2(){
        return new FanoutExchange(topic);
    }

    @Bean
    public Queue message() {
        return new Queue("ws-test");
    }

}
