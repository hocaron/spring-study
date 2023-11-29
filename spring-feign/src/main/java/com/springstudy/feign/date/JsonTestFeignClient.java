package com.springstudy.feign.date;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "member-main-private-api", url = "http://date.jsontest.com")
public interface JsonTestFeignClient {

    /**
     * 현재 날짜 조회
     */
    @GetMapping
    Response getDate();
}
