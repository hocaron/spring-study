package com.springstudy.jpa.member;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springstudy.jpa.organization.Organization;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public void save(@RequestParam(required = false) String param1, @RequestParam(required = false) String param2) {
        memberService.save("testNickname");
    }

    @PostMapping("/save-and-flush")
    public Member saveAndFlush() {
        return memberService.saveAndFlush("testNickname");
    }

    @PostMapping("/save-and-update")
    public Member saveAndUpdate() {
        return memberService.saveAndUpdate("testNickname");
    }

    @PostMapping("/save-and-flush-update")
    public Member saveAndFlushAndUpdate() {
        return memberService.saveAndFlushAndUpdate("testNickname");
    }
}
