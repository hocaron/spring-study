package com.springstudy.jpa.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class MultiThreadTest {
    @Autowired
    MemberService memberService;

    @Test
    public void testSummationWithConcurrency() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                try {
                } catch (Exception e) {
                    // Handle exception
                }
                latch.countDown();
            });
        }
        latch.await();
    }
}
