package com.study.warmup.io.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Column(name = "order_price")
    private BigDecimal orderPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @PrePersist
    public void prePersist() {
        this.totalPrice = this.orderPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
