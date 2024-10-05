package com.springstudy.featureflag.springfeatureflag.abtest;

import com.springstudy.featureflag.springfeatureflag.feature.FeatureFlagClientType;
import com.springstudy.featureflag.springfeatureflag.feature.RollbackCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    @RollbackCheck(client = FeatureFlagClientType.ROLLBACK)
    public String executeRollbackFeatureFlagLogic() {
        return testService.executeRollbackFeatureFlagLogic();
    }

    @GetMapping("/ab-test")
    public String executeFeatureFlagLogic() {
        return testService.executeFeatureFlagLogic();
    }
}
