package ru.comavp.proxy.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.dto.TestRequest;

import java.time.LocalDateTime;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ProducerProperties producerProperties;

    public void KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> sendMessage(TestRequest testRequest) {
        String message = testRequest.data() + "_" + LocalDateTime.now();
        System.out.println("Sending message: " + message);
        kafkaTemplate.send(producerProperties.getTopicName(), message);
        return Mono.empty();
    }
}
