package com.springstudy.sociallogin.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(
    name = "kakao-auth-api",
    url = "${client.kakao-auth.url}",
    configuration = KakaoFeignClientConfiguration.class
)
public interface KakaoAuthFeignClient {

    /**
     * 카카오 토큰 받기.
     *
     * @see <a href="https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#get-token-info">참고 링크</a>
     */
    @PostMapping("/oauth/token")
    KakaoSocialTokenResponse getToken(
        @RequestParam("grant_type") String grantType,
        @RequestParam("code") String code,
        @RequestParam("redirect_uri") String redirectUri,
        @RequestParam("client_id") String clientId,
        @RequestParam("client_secret") String clientSecret
    );
}
