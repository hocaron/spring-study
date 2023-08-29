package com.springstudy.sociallogin.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(
    name = "apple-auth",
    url = "${client.apple-auth.url}",
    configuration = AppleFeignClientConfiguration.class
)
public interface AppleAuthClient {

    @PostMapping("/auth/token")
    AppleSocialTokenInfoResponse getIdToken(
        @RequestParam("client_id") String clientId,
        @RequestParam("client_secret") String clientSecret,
        @RequestParam("grant_type") String grantType,
        @RequestParam("code") String code
    );
}
