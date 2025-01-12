package ru.comavp.proxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.comavp.proxy.dto.TestRequest;
import ru.comavp.proxy.dto.TestResponse;
import ru.comavp.proxy.service.ProxyService;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Autowired
    private ProxyService proxyService;

    @PostMapping("/send")
    public Mono<ResponseEntity<TestResponse>> sendRequest(@RequestBody TestRequest testRequest) {
        System.out.println("Received request with body: " + testRequest);
        return proxyService.sendRequest(testRequest)
                .map(ResponseEntity::ok);
    }
}
