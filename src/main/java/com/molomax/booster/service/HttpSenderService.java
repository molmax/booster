package com.molomax.booster.service;

import com.molomax.booster.config.WebClientConfig;
import com.molomax.booster.model.QuoteResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HttpSenderService {
    private final WebClientConfig config;

    public Flux<QuoteResponse> getRandomQuotes(int limit) {
        return config.getWebClient()
                .get()
                .accept()
                .exchangeToFlux(response -> response.bodyToFlux(QuoteResponse.class))
                .repeat(limit - 1);
    }
}
