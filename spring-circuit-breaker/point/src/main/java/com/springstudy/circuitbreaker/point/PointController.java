package com.springstudy.circuitbreaker.point;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/points")
public class PointController {

    @GetMapping
    public String get(@RequestParam String type) throws InterruptedException {

        return switch (type) {
            case "PASS" -> "PASS";
            case "READ_TIME_OUT" -> {
                Thread.sleep(5000);
                yield "PASS";
            }
            default -> throw new RuntimeException();
        };
    }
}
