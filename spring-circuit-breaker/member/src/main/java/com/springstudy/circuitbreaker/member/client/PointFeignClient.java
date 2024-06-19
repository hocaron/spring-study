package com.springstudy.circuitbreaker.member.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "point-api", url = "localhost:8090")
public interface PointFeignClient {

	@GetMapping("/points")
	String getPoints(@RequestParam String type);
}
