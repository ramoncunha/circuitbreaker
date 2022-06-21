package com.circuitbreakermvp.ServiceA.controller;

import com.circuitbreakermvp.ServiceA.infrastructure.PongProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
@RequiredArgsConstructor
public class PingController {

    private final PongProvider pongProvider;

    @GetMapping
    public String pingA() {
        return pongProvider.pong();
    }
}
