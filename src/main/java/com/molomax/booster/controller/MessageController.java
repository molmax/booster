package com.molomax.booster.controller;

import com.molomax.booster.http.HttpSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final HttpSender httpSender;

    @GetMapping(value = "/messages/random")
    public Mono<String> getRandomMessage() {
        return httpSender.getRandomMessage();
    }
}
