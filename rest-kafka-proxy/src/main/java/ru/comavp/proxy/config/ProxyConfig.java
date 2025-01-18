package ru.comavp.proxy.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties({ProxyProperties.class, KafkaMessageMapperProperties.class})
public class ProxyConfig {

    @Bean
    public WebClient webClient(ProxyProperties properties) {
        return WebClient.create(properties.getBaseUrl());
    }
}
