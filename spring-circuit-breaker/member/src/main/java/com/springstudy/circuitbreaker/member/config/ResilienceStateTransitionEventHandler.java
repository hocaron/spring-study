package com.springstudy.circuitbreaker.member.config;

import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import io.github.resilience4j.core.EventConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ResilienceStateTransitionEventHandler implements EventConsumer<CircuitBreakerOnStateTransitionEvent> {


    @Override
    public void consumeEvent(CircuitBreakerOnStateTransitionEvent event) {

        log.info(event.toString());
    }
}
