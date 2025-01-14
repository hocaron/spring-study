package com.study.warmup.warmup;

import com.study.warmup.io.entity.Member;
import com.study.warmup.io.entity.Order;
import com.study.warmup.io.entity.OrderItem;
import com.study.warmup.io.entity.Product;
import com.study.warmup.io.repository.MemberRepository;
import com.study.warmup.io.repository.OrderItemRepository;
import com.study.warmup.io.repository.OrderRepository;
import com.study.warmup.io.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationWarmup {

    private static final int WARMUP_ITERATIONS = 400;

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(readOnly = true)
    public void warmup() {
        log.info("애플리케이션 웜업 시작 - {}회 반복", WARMUP_ITERATIONS);
        long startTime = System.currentTimeMillis();

        try {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                if (i % 50 == 0) {
                    log.info("웜업 진행중: {}/{}", i, WARMUP_ITERATIONS);
                }
                executeWarmupQueries();
            }
        } catch (Exception e) {
            log.error("웜업 중 에러 발생", e);
        }

        long endTime = System.currentTimeMillis();
        log.info("애플리케이션 웜업 완료. 총 소요시간: {}ms, 평균 소요시간: {}ms/회",
            endTime - startTime,
            (endTime - startTime) / WARMUP_ITERATIONS);
    }

    private void executeWarmupQueries() {
        try {
            executeOrderDetailQuery();
            executeMemberOrdersQuery();
            executePaidOrdersQuery();
            executeMembersInAreaQuery();
            executeCategoryProductsQuery();
            executeOrderItemsQuery();
        } catch (Exception e) {
            log.warn("쿼리 실행 중 에러 발생", e);
        }
    }

    private void executeOrderDetailQuery() {
        Order order = orderRepository.findById(1L).orElse(null);
        if (order != null) {
            order.getOrderItems().size(); // lazy loading 강제
            order.getMember().getEmail(); // lazy loading 강제
        }
    }

    private void executeMemberOrdersQuery() {
        List<Order> memberOrders = orderRepository.findByMemberId(1L);
        memberOrders.forEach(o -> o.getOrderItems().size()); // lazy loading 강제
    }

    private void executePaidOrdersQuery() {
        List<Order> paidOrders = orderRepository.findByStatus(Order.OrderStatus.PAID);
        paidOrders.forEach(o -> o.getMember().getName()); // lazy loading 강제
    }

    private void executeMembersInAreaQuery() {
        List<Member> membersInArea = memberRepository.findByAddressZipCode("12345");
        membersInArea.forEach(m -> m.getOrders().size()); // lazy loading 강제
    }

    private void executeCategoryProductsQuery() {
        List<Product> categoryProducts = productRepository.findByCategoryId(1L);
        categoryProducts.forEach(p -> p.getOrderItems().size()); // lazy loading 강제
    }

    private void executeOrderItemsQuery() {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(1L);
        orderItems.forEach(oi -> {
            oi.getProduct().getName(); // lazy loading 강제
            oi.getOrder().getStatus(); // lazy loading 강제
        });
    }
}
