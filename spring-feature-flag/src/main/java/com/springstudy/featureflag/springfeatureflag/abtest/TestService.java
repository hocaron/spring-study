package com.springstudy.featureflag.springfeatureflag.abtest;

import com.springstudy.featureflag.springfeatureflag.feature.FeatureFlagClientType;
import dev.openfeature.sdk.OpenFeatureAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final OpenFeatureAPI openFeatureAPI;

    public String executeRollbackFeatureFlagLogic() {
        return "Feature Flag Disabled";
    }

    public String executeFeatureFlagLogic() {
        var client = openFeatureAPI.getClient(FeatureFlagClientType.ROLLBACK.getClientName());
        boolean isFeatureEnabled = client.getBooleanValue(FeatureFlagClientType.ROLLBACK.getClientName(), false);

        if (isFeatureEnabled) {
            return executeALogic();
        } else {
            return executeBLogic();
        }
    }

    private String executeALogic() {
        return "Feature Flag Enabled";
    }

    private String executeBLogic() {
        return "Feature Flag Disabled";
    }
}
