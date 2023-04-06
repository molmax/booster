package com.molomax.booster.http;

import com.molomax.booster.config.WebClientConfiguration;
import com.molomax.booster.model.QuoteResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HttpSender {
    private final WebClientConfiguration config;

    public Flux<QuoteResponse> getRandomQuotes(int limit) {
        return config.getWebClient()
                .get()
                .accept()
                .exchangeToFlux(response -> response.bodyToFlux(QuoteResponse.class))
                .repeat(limit - 1);
    }
}
