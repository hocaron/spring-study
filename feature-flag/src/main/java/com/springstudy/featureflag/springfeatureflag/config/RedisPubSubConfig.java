package com.springstudy.featureflag.springfeatureflag.config;

import com.springstudy.featureflag.springfeatureflag.feature.FeatureFlagService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisPubSubConfig {

    public static final String FEATURE_FLAG_TOPIC = "feature_flag";

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
                                                        MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic(FEATURE_FLAG_TOPIC));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(FeatureFlagService featureFlagService) {
        return new MessageListenerAdapter(featureFlagService, "handleMessage");
    }
}
