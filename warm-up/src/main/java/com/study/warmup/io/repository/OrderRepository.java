package com.study.warmup.io.repository;

import com.study.warmup.io.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByMemberId(Long memberId);
    List<Order> findByStatus(Order.OrderStatus status);
}
