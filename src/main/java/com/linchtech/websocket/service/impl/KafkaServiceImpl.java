package com.linchtech.websocket.service.impl;

import com.linchtech.websocket.service.KafkaService;
import org.springframework.stereotype.Service;


@Service
public class KafkaServiceImpl implements KafkaService {
/*
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
    }*/
}
