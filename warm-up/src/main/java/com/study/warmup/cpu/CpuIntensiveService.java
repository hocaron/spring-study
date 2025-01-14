package com.study.warmup.cpu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CpuIntensiveService {

    public long calculateFactorial(int number) {
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public String processString(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                result.append(Character.toUpperCase(c));
            } else if (Character.isDigit(c)) {
                result.append((char)(c + 1));
            } else {
                result.append('_');
            }
        }
        return result.toString();
    }

    public long doHeavyComputation(int iterations) {
        long sum = 0;
        for (int i = 0; i < iterations; i++) {
            sum += calculateFactorial(i % 10);
            if (i % 2 == 0) {
                sum -= calculateFactorial((i + 1) % 10);
            }
        }
        return sum;
    }
}
