package ru.comavp.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka-consumer.topic-name}", groupId = "kafka-consumer-group")
    public void receiveMessage(@Payload String message) {
        log.info("Received message: {}", message);
    }
}
