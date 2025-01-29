package com.study.warmup.io.service;

import com.study.warmup.io.entity.Member;
import com.study.warmup.io.entity.Order;
import com.study.warmup.io.entity.OrderItem;
import com.study.warmup.io.repository.OrderItemRepository;
import com.study.warmup.io.repository.OrderRepository;
import com.study.warmup.io.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public BigDecimal getOrderInformation(Long orderId) {

        Order order = getOrderById(orderId);
        getCategoryProductsForOrder(order);
        getOrdersByMember(order.getMember());
        getOrdersByStatus(order.getStatus());

        return order.getTotalAmount();
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    private void getCategoryProductsForOrder(Order order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

        for (OrderItem item : orderItems) {
            Long categoryId = item.getProduct().getCategory().getId();
            productRepository.findByCategoryId(categoryId);
        }
    }

    private void getOrdersByMember(Member member) {
        orderRepository.findByMemberId(member.getId());
    }

    private void getOrdersByStatus(Order.OrderStatus status) {
        orderRepository.findByStatus(status);
    }
}
