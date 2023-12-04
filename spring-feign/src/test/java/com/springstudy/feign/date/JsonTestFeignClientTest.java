package com.springstudy.feign.date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.springstudy.feign.date.TestConstants.*;

@SpringBootTest
public class JsonTestFeignClientTest {

    @Autowired
    JsonTestFeignClient jsonTestFeignClient;

    @Test
    void multiThreadTest() {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL);
        StopWatch sw = new StopWatch();

        sw.start();
        for (int i = 0; i < LOOP_COUNT; i++) {
            executorService.execute(() -> {
                jsonTestFeignClient.getDate();
            });
        }

        // 모든 스레드의 실행을 기다림
        while (!executorService.isTerminated()) {}
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}
