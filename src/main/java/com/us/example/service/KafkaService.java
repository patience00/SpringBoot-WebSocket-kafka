package com.us.example.service;


public interface KafkaService {

    /**
     * 发布kafka数据
     * @param message
     */
    boolean produce(String message);
}
