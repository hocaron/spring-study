package com.springstudy.featureflag.springfeatureflag.feature;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature-flag")
@RequiredArgsConstructor
public class FeatureFlagController {

    private final FeatureFlagService featureFlagService;

    @PostMapping("/publish")
    public void syncFeatureFlags(@RequestParam FeaturePubSubMessage message) {
        featureFlagService.publishFeature(FeatureFlagClientType.ROLLBACK, message);
    }
}
