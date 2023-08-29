package com.springstudy.sociallogin;

import com.springstudy.sociallogin.apple.GetMemberInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("default")
public class AppleAuthClientTest {

    @Autowired
    GetMemberInfoService getMemberInfoService;

    @Test
    void getToken() {
        String authorizationCode = "애플 인가코드";

        var source = getMemberInfoService.get(authorizationCode);
    }
}
