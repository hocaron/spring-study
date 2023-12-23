package com.springstudy.circuitbreaker.member.controller;

import com.springstudy.circuitbreaker.member.client.PointFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final PointFeignClient pointFeignClient;

    @GetMapping("/{type}")
    public String ping(@PathVariable String type) {

        if ("PASS".equals(type)) {
            return "pong";
        }

        throw new RuntimeException();
    }
}
