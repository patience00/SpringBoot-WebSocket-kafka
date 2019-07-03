// package com.us.example.config;
//
// import org.apache.kafka.clients.producer.KafkaProducer;
// import org.apache.kafka.clients.producer.ProducerConfig;
// import org.apache.kafka.common.serialization.StringSerializer;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import java.util.Properties;
//
//
// @Configuration
// public class KafkaConfig {
//
//     @Value("${spring.kafka.bootstrap-servers}")
//     private String servers;
//
//     public final String SECURITY_AUTH_CONFIG = "java.security.auth.login.config";
//     private KafkaProducer<String, String> producerQtSensorUp;
//
//     @Bean(name="kafkaProducer")
//     public KafkaProducer<String, String> getProducerQtSensorUpWithOutSSL() {
//         if (producerQtSensorUp == null) {
//             Properties props = new Properties();
//             props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
//             props.put(ProducerConfig.RETRIES_CONFIG, 0);
//             props.put(ProducerConfig.BATCH_SIZE_CONFIG, 10240);
//             props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//             props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 10240);
//             props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//             props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//             producerQtSensorUp = new KafkaProducer<>(props);
//         }
//         return producerQtSensorUp;
//     }
// }
