package ru.comavp.proxy.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.dto.KafkaMessage;

@Component
public class KafkaProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ProducerProperties producerProperties;

    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ProducerProperties producerProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.producerProperties = producerProperties;
    }

    public void KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> sendMessage(String requestToRedirect) {
        if (!producerProperties.isEnable()) {
            log.info("Kafka proxying is disabled");
            return Mono.empty();
        }
        String message = buildKafkaMessage(requestToRedirect);
        log.info("Sending message: {}", message);
        kafkaTemplate.send(producerProperties.getTopicName(), message);
        return Mono.empty();
    }

    private String buildKafkaMessage(String requestToRedirect) {
        try {
            return MAPPER.writeValueAsString(new KafkaMessage(requestToRedirect));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
