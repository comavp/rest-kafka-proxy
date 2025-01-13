package ru.comavp.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka-consumer")
@Getter
@Setter
public class ConsumerProperties {

    private String topicName;
    private String bootstrapServers;
}
