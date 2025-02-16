package com.springstudy.concurrency;

import com.springstudy.concurrency.racecondition.SynchronizedCounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class VirtualThreadPinningTest {

    private Object lock;
    private ReentrantLock reentrantLock;
    private SynchronizedCounterService synchronizedCounterService = new SynchronizedCounterService();

    @BeforeEach
    void setUp() {
        lock = new Object();
        reentrantLock = new ReentrantLock();
    }

    private void runConcurrentTasks(Runnable task) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                executor.submit(task);
            }
        }
    }

    @Test
    @DisplayName("Virtual Thread에서 synchronized 사용 시 Carrier Thread에 Pinning 발생 여부 테스트")
    void testSynchronizedPinning() {
        Runnable synchronizedTask = () -> {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        runConcurrentTasks(synchronizedTask);
    }

    @Test
    @DisplayName("Virtual Thread에서 ReentrantLock 사용 시 Carrier Thread에 Pinning 발생 여부 테스트")
    void testReentrantLockPinning() {
        Runnable reentrantLockTask = () -> {
            reentrantLock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                reentrantLock.unlock();
            }
        };
        runConcurrentTasks(reentrantLockTask);
    }

    @Test
    @DisplayName("Virtual Thread에서 락 없이 실행하는 경우 (비교 테스트)")
    void testLockFreeExecution() {
        Runnable lockFreeTask = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
        runConcurrentTasks(lockFreeTask);
    }
}
