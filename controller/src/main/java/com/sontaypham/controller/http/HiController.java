package com.sontaypham.controller.http;

import com.sontaypham.service.event.EventApplicationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequestMapping("/hi")
public class HiController {
    EventApplicationService eventApplicationService;
    RestTemplate restTemplate;
    public HiController(EventApplicationService eventApplicationService, RestTemplate restTemplate) {
        this.eventApplicationService = eventApplicationService;
        this.restTemplate = restTemplate;
    }
    @GetMapping("/v1")
    @RateLimiter(name = "backendA" , fallbackMethod = "v1FallBackMethod")
    public String sayHiVersion1() {
        return eventApplicationService.sayHi("SonTayPham sayHi v1");
    }
    public String v1FallBackMethod() {
        return "Too many requests to say hi version 1 !";
    }
    @GetMapping("/v2")
    @RateLimiter(name = "backendB" , fallbackMethod = "v2FallBackMethod")
    public String sayHiVersion2() {
        return eventApplicationService.sayHi("SonTayPham sayHi v2");
    }
    public String v2FallBackMethod() {
        return "Too many requests to say hi version 2 !";
    }
    private static final SecureRandom random = new SecureRandom();
    @GetMapping("/circuitBreaker")
    @CircuitBreaker(name = "checkRandom" , fallbackMethod = "circuitBreakerFallBackMethod")
    public String circuitBreaker() {
        int productId = random.nextInt(20) + 1;
        String url = "https://fakestoreapi.com/products/" + productId;
        return restTemplate.getForObject(url, String.class);
    }
    public String circuitBreakerFallBackMethod() {
        return "Circuit breaker OPEN state !";
    }
}
