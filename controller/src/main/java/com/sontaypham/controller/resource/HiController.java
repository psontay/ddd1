package com.sontaypham.controller.resource;

import com.sontaypham.service.event.EventApplicationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;

@RestController
@RequestMapping("/hello")
public class HiController {
    private final EventApplicationService eventApplicationService;
    private final RestTemplate restTemplate;
    public HiController(EventApplicationService eventApplicationService, RestTemplate restTemplate) {
        this.eventApplicationService = eventApplicationService;
        this.restTemplate = restTemplate;
    }
    SecureRandom random = new SecureRandom();
    @GetMapping("/v0")
    @RateLimiter(name = "backendA" , fallbackMethod = "fallBackMethodA")
    public String sayHi() {
        return eventApplicationService.sayHi("Trung Coc");
    }
    public String fallBackMethodA( Throwable throwable) {
        return "fallBackMethodA";
    }
    @GetMapping("/v1")
    @RateLimiter(name = "backendB" , fallbackMethod = "fallBackMethodB")
    public String anhTraiSayHi() {
        return eventApplicationService.sayHi("Minh Sac");
    }
    public String fallBackMethodB( Throwable throwable) {
        return "fallBackMethodB";
    }
    @GetMapping("/circuit/breaker")
    @CircuitBreaker(name = "checkRandom" , fallbackMethod = "fallBackMethodCircuitBreaker")
    public String circuitBreaker() {
        int productId = random.nextInt(20) + 1;
        String url = "https://fakestoreapi.com/products/" + productId;
        return restTemplate.getForObject(url, String.class);
    }
    public String fallBackMethodCircuitBreaker( Throwable throwable) {
        return "fallBackMethodCircuitBreaker";
    }
}
