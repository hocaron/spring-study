package com.study.warmup.io.service;

import com.study.warmup.io.entity.Member;
import com.study.warmup.io.entity.Order;
import com.study.warmup.io.entity.OrderItem;
import com.study.warmup.io.entity.Product;
import com.study.warmup.io.repository.MemberRepository;
import com.study.warmup.io.repository.OrderItemRepository;
import com.study.warmup.io.repository.OrderRepository;
import com.study.warmup.io.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberService memberService;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public BigDecimal getOrderInformation(Long orderId) {

        memberService.save();

        Order order = getOrderById(orderId);
        getCategoryProductsForOrder(order);
        getOrdersByMember(order.getMember());
        getOrdersByStatus(order.getStatus());
        getMembersInSameArea(order.getMember());
        checkProductStockForOrderItems(order.getOrderItems());

        return order.getTotalAmount();
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    private Map<String, List<Product>> getCategoryProductsForOrder(Order order) {
        Map<String, List<Product>> categoryProducts = new HashMap<>();
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

        for (OrderItem item : orderItems) {
            Long categoryId = item.getProduct().getCategory().getId();
            List<Product> productsInCategory = productRepository.findByCategoryId(categoryId);
            categoryProducts.put("category_" + categoryId, productsInCategory);
        }

        return categoryProducts;
    }

    private List<Order> getOrdersByMember(Member member) {
        return orderRepository.findByMemberId(member.getId());
    }

    private List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    private List<Member> getMembersInSameArea(Member member) {
        return memberRepository.findByAddressZipCode(member.getAddress().getZipCode());
    }

    private void checkProductStockForOrderItems(List<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            productRepository.findById(item.getProduct().getId())
                .orElse(null);
        }
    }
}
