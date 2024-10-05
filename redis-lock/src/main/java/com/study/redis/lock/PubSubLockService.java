package com.study.redis.lock;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PubSubLockService implements LockService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor(); // Background thread for pub/sub

    @Override
    public String tryLock(String lockKey, long timeout) {
        String lockValue = UUID.randomUUID().toString();

        // 먼저 락을 시도
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(success)) {
            return lockValue;  // 락 획득 성공
        }

        // 락 획득 실패한 경우, pub/sub으로 해제 신호를 대기
        CountDownLatch latch = new CountDownLatch(1);

        // Redis Connection을 사용하여 수동으로 구독
        executorService.submit(() -> {
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            connection.subscribe((message, pattern) -> {
                if (lockKey.equals(new String(message.getBody()))) {
                    latch.countDown();  // 락 해제 신호를 받으면 대기 해제
                }
            }, lockKey.getBytes());  // 해당 lockKey에 대한 해제 신호를 구독
        });

        try {
            // 타임아웃까지 대기 후 재시도
            if (latch.await(timeout, TimeUnit.SECONDS)) {
                // 다시 락 시도
                success = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, TimeUnit.SECONDS);
                return Boolean.TRUE.equals(success) ? lockValue : null;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return null;
    }

    @Override
    public void unlock(String lockKey, String lockValue) {
        String currentValue = redisTemplate.opsForValue().get(lockKey);
        if (lockValue.equals(currentValue)) {
            redisTemplate.delete(lockKey);  // 락 해제
            redisTemplate.convertAndSend(lockKey, lockKey);  // 해제 신호 전송
        }
    }
}
