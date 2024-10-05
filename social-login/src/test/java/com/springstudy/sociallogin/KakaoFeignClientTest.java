package com.springstudy.sociallogin;

import com.springstudy.sociallogin.kakao.KakaoFeignClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("default")
public class KakaoFeignClientTest {

    @Autowired
    KakaoFeignClient kakaoFeignClient;

    @Test
    void getUserInfoTest() {
        String authorization = "카카오 엑세스 토큰";

        var source = kakaoFeignClient.getUserInfo("Bearer " + authorization);
        Assertions.assertThat(source.getProperties().getNickname()).isEqualTo("카카오 닉네임");
    }
}
