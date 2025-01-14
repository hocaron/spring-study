package com.study.warmup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WarmUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarmUpApplication.class, args);
	}
}
