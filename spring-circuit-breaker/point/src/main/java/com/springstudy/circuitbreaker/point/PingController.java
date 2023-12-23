package com.springstudy.circuitbreaker.point;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping("/{type}")
    public String ping(@PathVariable String type) {

        if ("PASS".equals(type)) {
            return "pong";
        }

        throw new RuntimeException();
    }
}
