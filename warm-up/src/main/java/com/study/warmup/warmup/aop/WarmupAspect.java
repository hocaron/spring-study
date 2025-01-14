package com.study.warmup.warmup.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class WarmupAspect {

    private static final Logger log = LoggerFactory.getLogger(WarmupAspect.class);
    private static final ThreadLocal<Boolean> isWarmupMode = new ThreadLocal<>();

    @Around("@annotation(com.study.warmup.warmup.annotation.WarmupMode)")
    public Object handleWarmupMode(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            isWarmupMode.set(true);
            return joinPoint.proceed();
        } finally {
            isWarmupMode.remove();
        }
    }

    @Around("@annotation(com.study.warmup.warmup.annotation.SkipInWarmup)")
    public Object skipInWarmup(ProceedingJoinPoint joinPoint) throws Throwable {
        if (Boolean.TRUE.equals(isWarmupMode.get())) {
            log.debug("웜업 모드: 저장 로직 스킵 - {}", joinPoint.getSignature().getName());
            return null;
        }
        return joinPoint.proceed();
    }
}
