package ru.comavp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.comavp.dto.TestRequest;
import ru.comavp.dto.TestResponse;

@RestController
@RequestMapping("/api")
@Slf4j
public class MainController {

    @PostMapping("/main-endpoint")
    public ResponseEntity<TestResponse> processRequest(@RequestBody TestRequest testRequest) {
        log.info("Received request with body: {}", testRequest);
        return ResponseEntity.ok(new TestResponse("Hello from rest-server"));
    }
}
