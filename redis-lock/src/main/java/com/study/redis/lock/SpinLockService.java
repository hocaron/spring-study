package com.study.redis.lock;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SpinLockService implements LockService {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String tryLock(String lockKey, long timeout) {
        String lockValue = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        // 스핀락: timeout 시간 동안 반복해서 락을 시도
        while ((System.currentTimeMillis() - startTime) < (timeout * 1000)) {
            Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, TimeUnit.SECONDS);
            if (Boolean.TRUE.equals(success)) {
                return lockValue;  // 락 획득 성공
            }

            // 짧은 시간 대기 후 다시 시도
            try {
                Thread.sleep(100);  // 100ms 대기 후 다시 락 시도
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        return null;  // 타임아웃 내에 락을 획득하지 못함
    }

    @Override
    public void unlock(String lockKey, String lockValue) {
        String currentValue = redisTemplate.opsForValue().get(lockKey);
        if (lockValue.equals(currentValue)) {
            redisTemplate.delete(lockKey);  // 락 해제
        }
    }
}
