package ru.comavp.proxy.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.dto.TestRequest;

import java.time.LocalDateTime;

@Component
public class KafkaProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ProducerProperties producerProperties;

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ProducerProperties producerProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.producerProperties = producerProperties;
    }

    public void KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> sendMessage(TestRequest testRequest) {
        if (!producerProperties.isEnable()) {
            log.info("Kafka proxying is disabled");
            return Mono.empty();
        }
        String message = testRequest.data() + "_" + LocalDateTime.now();
        log.info("Sending message: {}", message);
        kafkaTemplate.send(producerProperties.getTopicName(), message);
        return Mono.empty();
    }
}
