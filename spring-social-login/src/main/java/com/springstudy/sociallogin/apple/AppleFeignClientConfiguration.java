package com.springstudy.sociallogin.apple;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class AppleFeignClientConfiguration {

    @Bean
    public AppleFeignClientErrorDecoder appleFeignClientErrorDecoder() {
        return new AppleFeignClientErrorDecoder(new ObjectMapper());
    }
}
