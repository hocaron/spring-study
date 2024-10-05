package com.springstudy.sociallogin.kakao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springstudy.common.MyServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class KakaoFeignClientErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    /**
     * 카카오 소셜 로그인 Feign API 연동 시 발생되는 오류에 대해서 예외 처리를 수행.
     *
     * @param methodKey Feign Client 메서드 이름
     * @param response  응답 정보
     * @return 예외를 리턴한다
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        Object body = null;
        if (response != null && response.body() != null) {
            try {
                body = objectMapper.readValue(response.body().toString(), Object.class);
            } catch (IOException e) {
                log.error("Error decoding response body", e);
            }
        }

        log.error("카카오 소셜 로그인 Feign API Feign Client 호출 중 오류가 발생되었습니다. body: {}", body);

        return new MyServerException(400, "카카오 소셜 로그인 Feign API Feign Client 호출 오류");
    }
}
