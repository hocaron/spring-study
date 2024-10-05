package com.springstudy.lock.job;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    @GetMapping("/increase")
    public String increaseSalary(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "salary") Integer salary
    ) {
        String result;
        jobService.increaseSalary(name, salary);
        result = "현재 가격 : " + jobService.currentSalary(name);
        log.info(result);
        return result;
    }

    @GetMapping("/increase/pessimisticLock")
    public String increaseSalaryWithPessimisticLock(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "salary") Integer salary
    ) {
        String result;
        jobService.increaseSalaryWithPessimisticLock(name, salary);
        result = "현재 가격 : " + jobService.currentSalary(name);
        log.info(result);
        return result;
    }
}
