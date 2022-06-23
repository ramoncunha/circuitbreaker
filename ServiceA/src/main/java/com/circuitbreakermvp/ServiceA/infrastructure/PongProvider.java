package com.circuitbreakermvp.ServiceA.infrastructure;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PongProvider {
    private static final String BASE_URL = "http://localhost:8081/";
    private static final String SERVICE_A = "serviceA";
    private final RestTemplate restTemplate;

    private int count = 0;

//    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "pongFallback")
//    @Retry(name = SERVICE_A)
    @RateLimiter(name = SERVICE_A)
    public String pong() {
        System.out.println("Retry method called " + ++count + " times at " + new Date());
        return restTemplate.getForObject(BASE_URL + "pong", String.class);
    }

    public String pongFallback(Exception ex) {
        return "This is a fallback method for Pong";
    }
}
