package com.springstudy.jpa.member;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springstudy.jpa.organization.Organization;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public Member save() {
        return memberService.save("testNickname");
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

    @GetMapping("/find-by-entity/{organizationId}")
    public List<Member> findByOrganization(@PathVariable Long organizationId) {
        System.out.println(organizationId);
        return memberService.findByOrganization(organizationId);
    }

    @GetMapping("/find-by-id/{organizationId}")
    public List<Member> findByOrganizationId(@PathVariable Long organizationId) {
        System.out.println(organizationId);
        return memberService.findByOrganizationId(organizationId);
    }
}
