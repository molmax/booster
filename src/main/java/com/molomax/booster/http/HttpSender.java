package com.molomax.booster.http;

import com.molomax.booster.config.WebClientConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HttpSender {
    private final WebClientConfiguration config;

    public Mono<String> getRandomMessage() {
        return config.getWebClient()
                .get()
                .accept()
                .retrieve()
                .bodyToMono(String.class);
    }
}
