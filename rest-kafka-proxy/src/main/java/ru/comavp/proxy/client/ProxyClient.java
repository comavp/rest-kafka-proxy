package ru.comavp.proxy.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.dto.TestRequest;
import ru.comavp.proxy.dto.TestResponse;

@Component
public class ProxyClient {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    public Mono<TestResponse> redirectRequest(TestRequest testRequest) {
        return webClient.post()
                .uri("/api/main-endpoint")
                .body(Mono.just(testRequest), TestRequest.class)
                .retrieve()
                .bodyToMono(TestResponse.class);
    }
}
