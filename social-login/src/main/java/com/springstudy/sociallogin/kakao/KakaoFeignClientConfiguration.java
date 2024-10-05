package com.springstudy.sociallogin.kakao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class KakaoFeignClientConfiguration {

    @Bean
    public KakaoFeignClientErrorDecoder kakaoAuthClientErrorDecoder() {
        return new KakaoFeignClientErrorDecoder(new ObjectMapper());
    }
}
