package ru.comavp.proxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.client.ProxyClient;
import ru.comavp.proxy.dto.TestRequest;
import ru.comavp.proxy.dto.TestResponse;
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

    public Mono<TestResponse> sendRequest(TestRequest testRequest) {
        return proxyClient.redirectRequest(testRequest)
                .doOnNext(testResponse -> kafkaProducer.sendMessage(testRequest));
    }
}
