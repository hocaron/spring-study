package com.springstudy.featureflag.springfeatureflag.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FeaturePubSubMessage {

    SYNC("sync"),
    ADD("add"),
    REMOVE("remove"),
    ;

    private final String message;

    public String publishMessage(FeatureFlagClientType clientType) {
        return String.format("%s:%s", message, clientType.getClientName());
    }

    public static FeaturePubSubMessage getRedisPubSubMessage(String message) {
        return Arrays.stream(values())
            .filter(pubSubMessage -> pubSubMessage.message.equals(message))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
