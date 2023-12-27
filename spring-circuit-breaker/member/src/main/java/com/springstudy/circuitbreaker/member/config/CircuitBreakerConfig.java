package com.springstudy.circuitbreaker.member.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class CircuitBreakerConfig {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Bean
    public CircuitBreaker pointCircuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("point-api_getPoints");
    }

    @Bean
    public CircuitBreakerNameResolver circuitBreakerNameResolver() {
        Set<CircuitBreaker> circuits = circuitBreakerRegistry.getAllCircuitBreakers();
        circuits.forEach(c -> c.getEventPublisher().onStateTransition(new ResilienceStateTransitionEventHandler()));

        return (feignClientName, target, method) -> feignClientName + "_" + method.getName();
    }

    @Bean
    public Set<CircuitBreaker> defaultCustomizer(
    ) {
        Set<CircuitBreaker> circuits = circuitBreakerRegistry.getAllCircuitBreakers();
        circuits.forEach(c -> c.getEventPublisher().onStateTransition(new ResilienceStateTransitionEventHandler()));

        return circuits;
    }
}
