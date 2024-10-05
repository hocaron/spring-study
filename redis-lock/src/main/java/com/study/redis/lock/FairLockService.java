package com.study.redis.lock;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class FairLockService implements LockService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String FAIR_LOCK_QUEUE_KEY = "fair_lock_queue";
    private static final long FAIR_LOCK_TIMEOUT = 60;  // 공정한 락 대기열 타임아웃

    @Override
    public String tryLock(String lockKey, long timeout) {
        String lockValue = UUID.randomUUID().toString();
        long currentTime = Instant.now().getEpochSecond();

        // 1. 대기열에 현재 클라이언트 추가
        redisTemplate.opsForZSet().add(FAIR_LOCK_QUEUE_KEY, lockValue, currentTime);

        // 2. 가장 먼저 락을 요청한 클라이언트가 락을 얻을 수 있는지 확인
        while (true) {
            Set<String> clients = redisTemplate.opsForZSet().range(FAIR_LOCK_QUEUE_KEY, 0, 0);
            if (clients != null && !clients.isEmpty() && clients.iterator().next().equals(lockValue)) {
                Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, TimeUnit.SECONDS);
                if (Boolean.TRUE.equals(success)) {
                    return lockValue;
                }
            }

            // 일정 시간이 지나면 대기열에서 제거
            long timeElapsed = Instant.now().getEpochSecond() - currentTime;
            if (timeElapsed > FAIR_LOCK_TIMEOUT) {
                redisTemplate.opsForZSet().remove(FAIR_LOCK_QUEUE_KEY, lockValue);
                break;
            }

            try {
                Thread.sleep(100);  // 잠시 대기 후 다시 확인
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        return null;
    }

    @Override
    public void unlock(String lockKey, String lockValue) {
        String currentValue = redisTemplate.opsForValue().get(lockKey);
        if (lockValue.equals(currentValue)) {
            redisTemplate.delete(lockKey);
            redisTemplate.opsForZSet().remove(FAIR_LOCK_QUEUE_KEY, lockValue);
        }
    }
}
