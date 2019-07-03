package com.linchtech.websocket;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = run(Application.class, args);
    }
}