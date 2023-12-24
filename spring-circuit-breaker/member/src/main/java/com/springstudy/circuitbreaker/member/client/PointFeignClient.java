package com.springstudy.circuitbreaker.member.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "point-api", url = "localhost:8090")
public interface PointFeignClient
{

    @GetMapping("/ping/{type}")
    String ping(@PathVariable String type);
}
