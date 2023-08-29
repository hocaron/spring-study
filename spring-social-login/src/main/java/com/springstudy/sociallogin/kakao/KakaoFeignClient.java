package com.springstudy.sociallogin.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(
    name = "kakao-api",
    url = "${client.kakao.url}",
    configuration = KakaoFeignClientConfiguration.class
)
public interface KakaoFeignClient {

    /**
     * 카카오 사용자 정보 조회.
     *
     * @param authorization 인증 토큰
     * @see <a href="https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info">참고 링크</a>
     */
    @GetMapping("/v2/user/me")
    KakaoUserInfoResponse getUserInfo(
        @RequestHeader("Authorization") String authorization
    );
}
