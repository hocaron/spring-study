package com.springstudy.jpa.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService.saveAll();
    }

    @Test
    @DisplayName("Pk로 조회 하면 더티체킹 일괄수행")
    void findByPkAndUpdate() {
        memberService.findByPkAndUpdate();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 조회 하면 더티체킹 바로 수행")
    void findByNonePkAndUpdate() {
        memberService.findByNonePkAndUpdate();
    }

    @AfterEach
    void clean() {
        memberService.deleteAll();
    }
}
