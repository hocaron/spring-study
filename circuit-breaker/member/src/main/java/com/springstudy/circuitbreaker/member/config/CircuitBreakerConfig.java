package com.springstudy.circuitbreaker.member.config;

import java.util.Set;

import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CircuitBreakerConfig {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Bean
    public CircuitBreaker pointCircuitBreaker() {
        return circuitBreakerRegistry.circuitBreaker("point-api_getPoints");
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
