package ru.comavp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka-consumer.topic-name}", groupId = "kafka-consumer-group")
    public void receiveMessage(@Payload String message) {
        System.out.println("Received message: " + message);
    }
}
