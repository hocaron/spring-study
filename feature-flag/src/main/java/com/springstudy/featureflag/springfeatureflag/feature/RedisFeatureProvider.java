package com.springstudy.featureflag.springfeatureflag.feature;

import dev.openfeature.sdk.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RedisFeatureProvider implements FeatureProvider {

    private final FeatureFlagService featureFlagService;

    @Override
    public Metadata getMetadata() {
        return () -> "RedisFeatureProvider";
    }

    @Override
    public ProviderEvaluation<Boolean> getBooleanEvaluation(String key, Boolean defaultValue, EvaluationContext context) {
        Boolean isEnabled = featureFlagService.isFeatureEnabled(FeatureFlagClientType.getFeatureFlagClientType(key));
        return ProviderEvaluation.<Boolean>builder()
            .value(isEnabled)
            .build();
    }

    @Override
    public ProviderEvaluation<String> getStringEvaluation(String key, String defaultValue, EvaluationContext context) {
        // 구현이 필요한 경우 추가
        return new ProviderEvaluation<>();
    }

    @Override
    public ProviderEvaluation<Integer> getIntegerEvaluation(String key, Integer defaultValue, EvaluationContext context) {
        // 구현이 필요한 경우 추가
        return new ProviderEvaluation<>();
    }

    @Override
    public ProviderEvaluation<Double> getDoubleEvaluation(String key, Double defaultValue, EvaluationContext context) {
        // 구현이 필요한 경우 추가
        return new ProviderEvaluation<>();
    }

    @Override
    public ProviderEvaluation<Value> getObjectEvaluation(String key, Value defaultValue, EvaluationContext context) {
        // 구현이 필요한 경우 추가
        return new ProviderEvaluation<>();
    }
}
