package com.springstudy.featureflag.springfeatureflag.feature;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class FeatureFlagService {

    private final FeatureFlagRepository featureFlagRepository;
    private final Map<FeatureFlagClientType, Boolean> FEATURE_FLAG = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        sync();
    }

    public void sync() {
        Arrays.stream(FeatureFlagClientType.values())
            .forEach(featureFlag -> FEATURE_FLAG.put(featureFlag, featureFlagRepository.getFeatureKeyByClient(featureFlag)));
    }

    public Boolean getFeatureFlag(FeatureFlagClientType client) {
        return FEATURE_FLAG.getOrDefault(client, false);
    }

    public boolean isFeatureEnabled(FeatureFlagClientType client) {
        return getFeatureFlag(client);
    }

    public void handleMessage(String message) {
        String[] parts = message.split(":");
        if (parts.length >= 2) {
            FeaturePubSubMessage eventType = FeaturePubSubMessage.getRedisPubSubMessage(parts[0]);
            FeatureFlagClientType client = FeatureFlagClientType.getFeatureFlagClientType(parts[1]);

            switch (eventType) {
                case ADD -> FEATURE_FLAG.put(client, true);
                case REMOVE -> FEATURE_FLAG.put(client, false);
                case SYNC -> sync();
            }
        }
    }

    public void publishFeature(FeatureFlagClientType client, FeaturePubSubMessage message) {

        switch (message){
            case ADD -> featureFlagRepository.save(client);
            case REMOVE -> featureFlagRepository.delete(client);
        }
        featureFlagRepository.publish(message.publishMessage(client));
    }
}
