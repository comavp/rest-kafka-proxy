package ru.comavp.proxy.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.config.KafkaMessageMapperProperties;
import ru.comavp.proxy.kafka.mapper.KafkaMessageMapper;

@Component
public class KafkaProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    private ProducerProperties producerProperties;
    private KafkaMessageMapperProperties kafkaMessageMapperProperties;
    private KafkaMessageMapper kafkaMessageMapper;

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ProducerProperties producerProperties,
                         KafkaMessageMapperProperties kafkaMessageMapperProperties, KafkaMessageMapper kafkaMessageMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.producerProperties = producerProperties;
        this.kafkaMessageMapperProperties = kafkaMessageMapperProperties;
        this.kafkaMessageMapper = kafkaMessageMapper;
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
        if (kafkaMessageMapperProperties.isEnable()) {
            log.info("Mapping Kafka message");
            return kafkaMessageMapper.map(requestToRedirect);
        }
        return requestToRedirect;
    }
}
