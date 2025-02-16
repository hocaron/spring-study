package com.springstudy.concurrency;

import com.springstudy.concurrency.racecondition.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CounterServiceTest {

    private CounterService counterService;
    private SynchronizedCounterService synchronizedCounterService;
    private ReentrantLockCounterService reentrantLockCounterService;
    private AtomicCounterService atomicCounterService;
    private SemaphoreCounterService semaphoreCounterService;

    @BeforeEach
    void setUp() {
        counterService = new CounterService();
        synchronizedCounterService = new SynchronizedCounterService();
        reentrantLockCounterService = new ReentrantLockCounterService();
        atomicCounterService = new AtomicCounterService();
        semaphoreCounterService = new SemaphoreCounterService();
    }

    private void runConcurrentIncrements(Runnable incrementFunction, int threadCount, int iterations) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount * iterations; i++) {
            executor.submit(incrementFunction);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Race Condition이 발생하는 기본 Counter 테스트")
    void testRaceCondition() throws InterruptedException {
        runConcurrentIncrements(counterService::increment, 10, 1000);
        System.out.println("Basic Counter: " + counterService.getCount());
        assertNotEquals(10000, counterService.getCount(), "Race Condition 발생 가능");
    }

    @Test
    @DisplayName("synchronized 키워드를 사용한 동기화 테스트")
    void testSynchronizedCounter() throws InterruptedException {
        runConcurrentIncrements(synchronizedCounterService::increment, 10, 1000);
        System.out.println("Synchronized Counter: " + synchronizedCounterService.getCount());
        assertEquals(10000, synchronizedCounterService.getCount(), "동기화로 Race Condition 방지");
    }

    @Test
    @DisplayName("ReentrantLock을 사용한 동기화 테스트")
    void testReentrantLockCounter() throws InterruptedException {
        runConcurrentIncrements(reentrantLockCounterService::increment, 10, 1000);
        System.out.println("ReentrantLock Counter: " + reentrantLockCounterService.getCount());
        assertEquals(10000, reentrantLockCounterService.getCount(), "ReentrantLock으로 Race Condition 방지");
    }

    @Test
    @DisplayName("AtomicInteger를 사용한 원자적 연산 테스트")
    void testAtomicCounter() throws InterruptedException {
        runConcurrentIncrements(atomicCounterService::increment, 10, 1000);
        System.out.println("Atomic Counter: " + atomicCounterService.getCount());
        assertEquals(10000, atomicCounterService.getCount(), "Atomic 연산으로 Race Condition 방지");
    }

    @Test
    @DisplayName("Semaphore를 사용한 동기화 테스트")
    void testSemaphoreCounter() throws InterruptedException {
        runConcurrentIncrements(semaphoreCounterService::increment, 10, 1000);
        System.out.println("Semaphore Counter: " + semaphoreCounterService.getCount());
        assertEquals(10000, semaphoreCounterService.getCount(), "Semaphore로 Race Condition 방지");
    }
}
