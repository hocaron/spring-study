package com.springstudy.circuitbreaker.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springstudy.circuitbreaker.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members/points")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public void getMemberInfo() {

        memberService.getMemberInfo(1);
    }
}
