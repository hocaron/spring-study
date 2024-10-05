package com.study.redis.lock;

public interface LockService {
    /**
     * 락을 시도합니다.
     *
     * @param lockKey   락을 설정할 키
     * @param timeout   락의 타임아웃 (초)
     * @return 락 획득 성공 시 락 해제를 위한 lockValue 반환, 실패 시 null 반환
     */
    String tryLock(String lockKey, long timeout);

    /**
     * 락을 해제합니다.
     *
     * @param lockKey   락을 해제할 키
     * @param lockValue 락을 설정할 때 사용한 값
     */
    void unlock(String lockKey, String lockValue);
}
