package com.springstudy.circuitbreaker.member.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.springstudy.circuitbreaker.member"})
public class FeignClientConfig {
}
