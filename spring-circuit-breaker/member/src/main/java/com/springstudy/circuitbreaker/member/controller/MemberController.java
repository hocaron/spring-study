package com.springstudy.circuitbreaker.member.controller;

import com.springstudy.circuitbreaker.member.client.PointFeignClient;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members/points")
@RequiredArgsConstructor
public class MemberController {

    private final PointFeignClient pointFeignClient;

    @GetMapping
    public String ping(@RequestParam String type) {

        return pointFeignClient.getPoints(type);
    }
}
