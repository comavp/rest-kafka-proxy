package ru.comavp.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka-consumer")
public class ConsumerProperties {

    private String topicName;
    private String bootstrapServers;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }
}
