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
            for (int i = 0; i < 900_000_000; i++) {
                executor.submit(task);
            }
        }
    }

    private static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Test
    @DisplayName("Virtual Thread에서 synchronized 사용 시 Carrier Thread에 Pinning 발생 여부 테스트(IO)")
    void testSynchronizedPinningWithIo() {
        Runnable synchronizedTask = () -> {
            try {
                System.out.println(Thread.currentThread());
                Thread.sleep(100);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        };
        runConcurrentTasks(synchronizedTask);
    }

    @Test
    @DisplayName("Virtual Thread에서 ReentrantLock 사용 시 Carrier Thread에 Pinning 발생 여부 테스트(IO)")
    void testReentrantLockPinningWithIo() {
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
    @DisplayName("Virtual Thread에서 synchronized 사용 시 Carrier Thread에 Pinning 발생 여부 테스트")
    void testSynchronizedPinning() {
        Runnable synchronizedTask = () -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " - Acquired Lock");
                // 락을 오래 보유하도록 해서 다른 Virtual Thread들이 대기하도록 만듦
                for (int i = 0; i < 100_000_000; i++) {
                    Math.sqrt(i); // CPU 부하 작업 (오래 걸리도록 설정)
                    Math.sqrt(i); // CPU 부하 작업 (오래 걸리도록 설정)
                    Math.sqrt(i); // CPU 부하 작업 (오래 걸리도록 설정)
                    Math.sqrt(i); // CPU 부하 작업 (오래 걸리도록 설정)
                }
                System.out.println(Thread.currentThread() + " - Released Lock");
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
                System.out.println(Thread.currentThread() + " - Acquired Lock");
                // 락을 오래 보유하도록 해서 다른 Virtual Thread들이 대기하도록 만듦
                for (int i = 0; i < 100_000_000; i++) {
                    long result = fibonacci(40); // 피보나치 계산 (CPU 부하)
                }
                System.out.println(Thread.currentThread() + " - Released Lock");
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
