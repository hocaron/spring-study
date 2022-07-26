package com.springstudy.lock.job;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class JobService {

    private final JobRepository jobRepository;

    public Integer currentSalary(String name) {
        Job job = jobRepository.findByName(name);
        return job.getSalary();
    }

    public Integer increaseSalary(String name, Integer salary) {
        Job job = jobRepository.findByName(name);
        job.increaseSalary(salary);
        return job.getSalary();
    }

    public Integer increaseSalaryWithPessimisticLock(String name, Integer salary) {
        Job job = jobRepository.findByNameWithPessimisticLock(name);
        job.increaseSalary(salary);
        return job.getSalary();
    }
}
