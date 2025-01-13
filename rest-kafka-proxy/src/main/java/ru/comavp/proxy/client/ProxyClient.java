package ru.comavp.proxy.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.config.ProxyProperties;
import ru.comavp.proxy.dto.TestRequest;
import ru.comavp.proxy.dto.TestResponse;

@Component
public class ProxyClient {

    private WebClient webClient;
    private ProxyProperties properties;

    @Autowired
    public ProxyClient(WebClient webClient, ProxyProperties properties) {
        this.webClient = webClient;
        this.properties = properties;
    }

    public Mono<TestResponse> redirectRequest(TestRequest testRequest) {
        return webClient.post()
                .uri(properties.getEndpoint())
                .body(Mono.just(testRequest), TestRequest.class)
                .retrieve()
                .bodyToMono(TestResponse.class);
    }
}
