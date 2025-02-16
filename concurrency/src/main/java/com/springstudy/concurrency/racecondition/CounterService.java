package com.springstudy.concurrency.racecondition;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private int count = 0; // 공유 자원

    public void increment() {
        count++; // 원자적 연산이 아님 → Race Condition 발생 가능
    }

    public int getCount() {
        return count;
    }
}
