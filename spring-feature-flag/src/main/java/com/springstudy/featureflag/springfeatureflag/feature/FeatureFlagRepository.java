package com.springstudy.featureflag.springfeatureflag.feature;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import static com.springstudy.featureflag.springfeatureflag.config.RedisPubSubConfig.FEATURE_FLAG_TOPIC;

@Repository
@RequiredArgsConstructor
public class FeatureFlagRepository {

    private final StringRedisTemplate stringRedisTemplate;

    public boolean getFeatureKeyByClient(FeatureFlagClientType client) {
        return "true".equals(stringRedisTemplate.opsForValue().get(client.getKey()));
    }

    public void save(FeatureFlagClientType client) {
        stringRedisTemplate.opsForValue().set(client.getKey(), "true");
    }

    public void delete(FeatureFlagClientType client) {
        stringRedisTemplate.delete(client.getKey());
    }

    public void publish(String message) {
        stringRedisTemplate.convertAndSend(FEATURE_FLAG_TOPIC, message);
    }
}
