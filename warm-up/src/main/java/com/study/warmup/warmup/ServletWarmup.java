package com.study.warmup.warmup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class ServletWarmup {

    private static final int WARMUP_ITERATIONS = 1000;

    private final WarmupFeignClient warmupFeignClient;

    @EventListener(ApplicationReadyEvent.class)
    public void warmup() {
        log.info("Feign Client 웜업 시작");
        long startTime = System.currentTimeMillis();

        executeWarmup();

        long endTime = System.currentTimeMillis();
        log.info("Feign Client 웜업 완료. 소요시간: {}ms", endTime - startTime);
    }

    private void executeWarmup() {
        IntStream.range(0, WARMUP_ITERATIONS).forEach(i -> {
            try {
                warmupFeignClient.getOrder(1L);
            } catch (Exception e) {
                log.warn("웜업 요청 실패: {}", e.getMessage());
            }
        });
    }
}
