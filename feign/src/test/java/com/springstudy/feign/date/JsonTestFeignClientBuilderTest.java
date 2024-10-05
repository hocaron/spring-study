package com.springstudy.feign.date;

import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.springstudy.feign.date.TestConstants.LOOP_COUNT;
import static com.springstudy.feign.date.TestConstants.THREAD_POOL;

public class JsonTestFeignClientBuilderTest {

    private static final String TEST_URL = "http://date.jsontest.com";

    @Test
    void multiThreadTestWithDefault() {

        executeWithMultiThread(createDefaultClient(), Executors.newFixedThreadPool(THREAD_POOL), new StopWatch());
    }

    @Test
    void multiThreadTestWithApache() {

        executeWithMultiThread(createApacheHttpClient(), Executors.newFixedThreadPool(THREAD_POOL), new StopWatch());
    }

    @Test
    void singleThreadTestWithDefault() {

        executeWithSingleThread(createDefaultClient(), new StopWatch());
    }

    @Test
    void singleThreadTestWithApache() {

        executeWithSingleThread(createApacheHttpClient(), new StopWatch());
    }

    private void executeWithMultiThread(JsonTestFeignClientBuilder client, ExecutorService executorService, StopWatch sw) {

        sw.start();
        for (int i = 0; i < LOOP_COUNT; i++) {
            executorService.execute(client::getDate);
        }

        // 모든 스레드의 실행을 기다림
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        sw.stop();
        System.out.println(sw.getTotalTimeMillis());
    }

    private void executeWithSingleThread(JsonTestFeignClientBuilder client, StopWatch sw) {

        sw.start();
        for (int i = 0; i < LOOP_COUNT; i++) {
            client.getDate();
        }
        sw.stop();
        System.out.println(sw.getTotalTimeMillis());
    }

    private JsonTestFeignClientBuilder createDefaultClient() {
        return Feign.builder()
                .target(JsonTestFeignClientBuilder.class, TEST_URL);
    }

    private JsonTestFeignClientBuilder createApacheHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);            // 전체 커넥션 수 제한
        cm.setDefaultMaxPerRoute(200);  // 각 라우트(호스트)당 최대 커넥션 수 제한

        // CloseableHttpClient를 생성할 때 PoolingHttpClientConnectionManager 사용
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        // Apache HttpClient를 사용하는 Feign 클라이언트
        return Feign.builder()
                .client(new ApacheHttpClient(httpClient))
                .target(JsonTestFeignClientBuilder.class, TEST_URL);
    }
}
