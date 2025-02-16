package com.springstudy.concurrency.racecondition;

import org.springframework.stereotype.Service;

@Service
public class SynchronizedCounterService {
    private int count = 0;

    public synchronized void increment() {
        try {
            Thread.sleep(1000); // 블로킹 연산
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
