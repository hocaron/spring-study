package com.springstudy.jpa.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
