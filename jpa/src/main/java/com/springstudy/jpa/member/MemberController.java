package com.springstudy.jpa.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final FindByService findByService;

    @GetMapping
    public void test() {
        findByService.findAllByNonePkInAndUpdate();
    }
}
