package com.linchtech.websocket;

/**
 * Created by yangyibo on 16/12/29.
 */
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = run(Application.class, args);
    }
}