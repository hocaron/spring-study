package com.springstudy.featureflag.springfeatureflag.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FeatureFlagClientType {

    ROLLBACK("feature:rollback", "rollback-client"),
    ;

    private final String key;
    private final String clientName;

    public static FeatureFlagClientType getFeatureFlagClientType(String clientName) {
        return Arrays.stream(values())
            .filter(client -> client.clientName.equals(clientName))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
