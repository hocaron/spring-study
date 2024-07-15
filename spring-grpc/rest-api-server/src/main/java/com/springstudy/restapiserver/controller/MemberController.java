package com.springstudy.restapiserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class MemberController {

    private static final Map<Long, MemberInfo> memberDatabase = new ConcurrentHashMap<>();

    static {
        memberDatabase.put(1L, MemberInfo.builder()
            .id(1L)
            .email("user123@example.com")
            .identification("ID123456789")
            .phoneNumber("010-1234-5678")
            .build());
        memberDatabase.put(2L, MemberInfo.builder()
            .id(2L)
            .email("user456@example.com")
            .identification("ID987654321")
            .phoneNumber("010-8765-4321")
            .build());
    }

    @GetMapping("/members/{id}")
    public MemberInfo getMemberInfo(@PathVariable Long id) {
        return memberDatabase.get(id);
    }
}
