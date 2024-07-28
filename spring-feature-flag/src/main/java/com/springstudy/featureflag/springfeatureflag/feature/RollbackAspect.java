package com.springstudy.featureflag.springfeatureflag.feature;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RollbackAspect {

    private final OpenFeatureAPI openFeatureAPI;

    @Around("@annotation(rollbackCheck)")
    public Object around(ProceedingJoinPoint joinPoint, RollbackCheck rollbackCheck) throws Throwable {
        String clientName = rollbackCheck.client().getClientName();
        Client client = openFeatureAPI.getClient(clientName);
        boolean isFeatureEnabled = client.getBooleanValue(clientName, false);

        if (isFeatureEnabled) {
            throw new RuntimeException("롤백이 활성화되어 작업을 중단합니다.");
        }
        return joinPoint.proceed();
    }
}
