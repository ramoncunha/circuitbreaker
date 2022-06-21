package com.circuitbreakermvp.ServiceA.infrastructure;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PongProvider {
    private static final String BASE_URL = "http://localhost:8081/";
    private static final String SERVICE_A = "serviceA";
    private final RestTemplate restTemplate;

    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "pongFallback")
    public String pong() {
        return restTemplate.getForObject(BASE_URL + "pong", String.class);
    }

    public String pongFallback(Exception ex) {
        return "This is a fallback method for Pong";
    }
}
