package com.springstudy.restapiserver.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfo {
    private long id;
    private String email;
    private String identification;
    private String phoneNumber;
}
