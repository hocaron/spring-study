package com.springstudy.feign.date;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/date")
@RequiredArgsConstructor
@Slf4j
public class DateController {

    private final JsonTestFeignClient jsonTestFeignClient;

    @GetMapping
    public Object get() {

        StopWatch sw = new StopWatch();
        sw.start();

        return jsonTestFeignClient.getDate();
    }
}
