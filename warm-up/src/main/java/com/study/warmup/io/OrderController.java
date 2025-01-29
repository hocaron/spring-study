package com.study.warmup.io;

import com.study.warmup.io.service.OrderService;
import com.study.warmup.warmup.annotation.WarmupMode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public BigDecimal getOrderInformation(@PathVariable Long orderId) {
        return orderService.getOrderInformation(orderId);
    }

    @WarmupMode
    @GetMapping("/warmup/{orderId}")
    public BigDecimal getOrderInformationForWarmUp(@PathVariable Long orderId) {
        return orderService.getOrderInformation(orderId);
    }
}
