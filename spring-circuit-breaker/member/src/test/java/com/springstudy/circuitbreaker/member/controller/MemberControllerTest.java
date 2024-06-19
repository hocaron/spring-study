package com.springstudy.circuitbreaker.member.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("default")
class MemberControllerTest {

	@Autowired
	MemberController memberController;

	@Test
	void getMemberInfoTest() {
		ExecutorService executorService = Executors.newFixedThreadPool(30);

		// 동시에 실행할 테스트 수
		int testCount = 30;

		for (int i = 0; i < testCount; i++) {
			// 각 테스트를 별도의 스레드에서 실행
			executorService.execute(() -> {
				memberController.getMemberInfo();
			});
		}

		// ExecutorService 종료를 기다림
		executorService.shutdown();
		try {
			// 모든 작업이 완료되거나 지정된 시간이 경과할 때까지 대기
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
