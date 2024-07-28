package com.springstudy.featureflag.springfeatureflag.feature;

import dev.openfeature.sdk.OpenFeatureAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeatureFlagConfig {

    private final RedisFeatureProvider redisFeatureProvider;

    @Bean
    public OpenFeatureAPI openFeatureAPI() {
        OpenFeatureAPI openFeatureAPI =  OpenFeatureAPI.getInstance();
        openFeatureAPI.setProvider(redisFeatureProvider);
        return openFeatureAPI;
    }
}
