package com.springstudy.circuitbreaker.member.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfig {

    @Bean
    public CircuitBreaker pointCircuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("pointCircuitBreaker");
    }
}
