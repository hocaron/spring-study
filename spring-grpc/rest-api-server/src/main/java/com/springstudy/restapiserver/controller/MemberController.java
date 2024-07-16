package com.springstudy.restapiserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public MemberInfo getMemberInfo(@PathVariable Long id,
                                    @RequestHeader("Header1") String header1,
                                    @RequestHeader("Header2") String header2,
                                    @RequestHeader("Header3") String header3,
                                    @RequestHeader("Header4") String header4,
                                    @RequestHeader("Header5") String header5,
                                    @RequestHeader("Header6") String header6,
                                    @RequestHeader("Header7") String header7,
                                    @RequestHeader("Header8") String header8,
                                    @RequestHeader("Header9") String header9) {
        String header1S = header1;
        String header2S = header2;
        String header3S = header3;
        String header4S = header4;
        String header5S = header5;
        String header6S = header6;
        String header7S = header7;
        String header8S = header8;
        String header9S = header9;

        return memberDatabase.get(id);
    }
}
