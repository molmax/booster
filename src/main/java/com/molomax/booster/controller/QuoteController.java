package com.molomax.booster.controller;

import com.molomax.booster.exceptions.InvalidRequestParameterException;
import com.molomax.booster.http.HttpSender;
import com.molomax.booster.model.QuoteResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuoteController {
    private final HttpSender httpSender;
    private static final int REQUESTS_LIMIT = 5;

    /**
     * Returns an array of flamboyant rap quotes
     *
     * @param limit - limit for number of outgoing http requests, must not exceed REQUESTS_LIMIT
     */
    @GetMapping(value = "/quotes")
    public Flux<QuoteResponse> getRandomQuotes(@RequestParam(value = "limit", defaultValue = "1") int limit) {
        if (limit <= 0) {
            limit = 1;
        }
        if (limit > REQUESTS_LIMIT) {
            var msg = String.format("Invalid request parameter 'limit'. Must not exceed %d.", REQUESTS_LIMIT);
            return Flux.error(new InvalidRequestParameterException(msg));
        }
        return httpSender.getRandomQuotes(limit);
    }
}
