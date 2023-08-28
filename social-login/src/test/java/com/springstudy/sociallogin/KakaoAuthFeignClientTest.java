package com.springstudy.sociallogin;

import com.springstudy.sociallogin.kakao.KakaoAuthFeignClient;
import com.springstudy.sociallogin.kakao.KakaoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("default")
public class KakaoAuthFeignClientTest {

    @Autowired
    KakaoAuthFeignClient kakaoAuthFeignClient;

    @Autowired
    KakaoProperties kakaoProperties;

    @Test
    void getToken() {
        String authorizationCode = "카카오 인가코드";
        String redirectUri = "카카오 리다이렉트 uri";

        var source = kakaoAuthFeignClient.getToken(
            kakaoProperties.getGrantType(),
            authorizationCode,
            redirectUri,
            kakaoProperties.getClientId(),
            kakaoProperties.getClientSecret()
        );
    }
}
