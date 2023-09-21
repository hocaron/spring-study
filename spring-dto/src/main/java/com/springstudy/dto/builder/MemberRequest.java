package com.springstudy.dto.builder;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MemberRequest {

    @NotNull
    private String job = "developer";

    private String job1;
}
