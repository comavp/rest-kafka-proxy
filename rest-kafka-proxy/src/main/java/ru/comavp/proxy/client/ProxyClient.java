package ru.comavp.proxy.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.config.ProxyProperties;

@Component
public class ProxyClient {

    private WebClient webClient;
    private ProxyProperties properties;

    @Autowired
    public ProxyClient(WebClient webClient, ProxyProperties properties) {
        this.webClient = webClient;
        this.properties = properties;
    }

    public Mono<String> redirectRequest(String requestToRedirect) {
        return webClient.post()
                .uri(properties.getEndpoint())
                .header("Content-Type", "application/json")
                .body(Mono.just(requestToRedirect), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
