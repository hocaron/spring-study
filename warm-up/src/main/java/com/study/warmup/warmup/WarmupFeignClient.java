package com.study.warmup.warmup;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "warm-up", url = "http://localhost:8080")
public interface WarmupFeignClient {

    @GetMapping("/api/orders/warmup/{orderId}")
    void getOrder(@PathVariable Long orderId);
}
