package com.study.warmup.warmup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ServletWarmup {
    private final WarmupFeignClient warmupFeignClient;

    @EventListener(ApplicationReadyEvent.class)
    public void warmup() {
        log.info("Feign Client 웜업 시작");
        long startTime = System.currentTimeMillis();

        try {
            runWarmupIterations();
        } catch (Exception e) {
            log.error("Feign 웜업 중 에러 발생", e);
        }

        long endTime = System.currentTimeMillis();
        log.info("Feign Client 웜업 완료. 소요시간: {}ms", endTime - startTime);
    }

    private void runWarmupIterations() {
        final int warmupIterations = 400;

        for (int i = 0; i < warmupIterations; i++) {
            executeWarmupQuery(i, warmupIterations);
        }
    }

    private void executeWarmupQuery(int currentIteration, int totalIterations) {
        warmupFeignClient.getOrder(1L);

        if (currentIteration % 50 == 0) {
            log.info("Feign 웜업 진행중: {}/{}", currentIteration, totalIterations);
        }
    }
}
