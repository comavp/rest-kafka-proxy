package ru.comavp.proxy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(ProxyController.class);

    @PostMapping("/send")
    public Mono<ResponseEntity<TestResponse>> sendRequest(@RequestBody TestRequest testRequest) {
        log.info("Received request with body: {}", testRequest);
        return proxyService.sendRequest(testRequest)
                .map(ResponseEntity::ok);
    }
}
