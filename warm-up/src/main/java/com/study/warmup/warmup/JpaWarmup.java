package com.study.warmup.warmup;

import com.study.warmup.io.entity.Order;
import com.study.warmup.io.repository.OrderItemRepository;
import com.study.warmup.io.repository.OrderRepository;
import com.study.warmup.io.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaWarmup {

    private static final int WARMUP_ITERATIONS = 1000;

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @PostConstruct
    public void warmup() {
        log.info("애플리케이션 웜업 시작 - {}회 반복", WARMUP_ITERATIONS);
        long startTime = System.currentTimeMillis();

        executeWarmup();

        long endTime = System.currentTimeMillis();
        log.info("애플리케이션 웜업 완료. 총 소요시간: {}ms, 평균 소요시간: {}ms/회",
            endTime - startTime, (endTime - startTime) / WARMUP_ITERATIONS);
    }

    private void executeWarmup() {
        IntStream.range(0, WARMUP_ITERATIONS).forEach(i -> {
            try {
                orderRepository.findById(1L);
                orderRepository.findByMemberId(1L);
                orderRepository.findByStatus(Order.OrderStatus.PAID);
                productRepository.findByCategoryId(1L);
                orderItemRepository.findByOrderId(1L);
            } catch (Exception e) {
                log.warn("웜업 요청 실패: {}", e.getMessage());
            }
        });
    }
}
