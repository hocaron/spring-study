package com.springstudy.feign.date;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "json-test-api", url = "${feign.client.json-test-api.url}")
public interface JsonTestFeignClient {

    /**
     * 현재 날짜 조회
     */
    @GetMapping
    Response getDate();
}
