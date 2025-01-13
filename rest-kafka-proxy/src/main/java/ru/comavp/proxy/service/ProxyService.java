package ru.comavp.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.client.ProxyClient;
import ru.comavp.proxy.kafka.KafkaProducer;

@Service
public class ProxyService {

    private ProxyClient proxyClient;
    private KafkaProducer kafkaProducer;

    @Autowired
    public ProxyService(ProxyClient proxyClient, KafkaProducer kafkaProducer) {
        this.proxyClient = proxyClient;
        this.kafkaProducer = kafkaProducer;
    }

    public Mono<String> sendRequest(String requestToRedirect) {
        return proxyClient.redirectRequest(requestToRedirect)
                .doOnNext(response -> kafkaProducer.sendMessage(requestToRedirect));
    }
}
