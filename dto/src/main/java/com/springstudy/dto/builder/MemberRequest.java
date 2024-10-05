package com.springstudy.dto.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@Getter
public class MemberRequest {

    @NotNull
    private String job = "developer";

    private String job1;
}
