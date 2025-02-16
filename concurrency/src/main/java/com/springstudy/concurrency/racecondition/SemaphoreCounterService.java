package com.springstudy.concurrency.racecondition;

import org.springframework.stereotype.Service;
import java.util.concurrent.Semaphore;

@Service
public class SemaphoreCounterService {
    private int count = 0;
    private final Semaphore semaphore = new Semaphore(1);

    public void increment() {
        try {
            semaphore.acquire();
            count++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    public int getCount() {
        return count;
    }
}
