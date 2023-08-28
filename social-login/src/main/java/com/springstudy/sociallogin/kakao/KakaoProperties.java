package com.springstudy.sociallogin.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "social-login.provider.kakao")
@Getter
@Setter
public class KakaoProperties {

    private String grantType;
    private String clientId;
    private String clientSecret;
}
