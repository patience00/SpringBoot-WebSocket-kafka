package com.us.example.service.impl;

import com.us.example.service.KafkaService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;


@Service
public class KafkaServiceImpl implements KafkaService {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Autowired
    KafkaProducer<String, String> kafkaProducer;

    @Override
    public boolean produce(String message) {
         Future<RecordMetadata> future = kafkaProducer.send(new ProducerRecord<>(topic, message));
        boolean isSuccess;
        try {
            RecordMetadata metadata = future.get();
            isSuccess = true;
//            logger.debug("produce ok: {}", "");
        } catch (Exception e) {
            isSuccess = false;
//            logger.error("produce fail", e);
        }
        kafkaProducer.flush();
        return isSuccess;
    }
}
