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
    void findByPkAndUpdateTest() {
        memberService.findByPkAndUpdate();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 조회 하면 더티체킹 바로 수행")
    void findByNonePkAndUpdateTest() {
        memberService.findByNonePkAndUpdate();
    }

    @Test
    @DisplayName("Pk로 In 절 조회 하면 더티체킹 일괄수행")
    void findAllByPkInAndUpdateTest() {
        memberService.findAllByPkInAndUpdate();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 In 절 조회 하면 더티체킹 바로 수행")
    void findAllByNonePkInAndUpdateTest() {
        memberService.findAllByNonePkInAndUpdate();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 조회 하면 쿼리는 한번 생성")
    void findByPkTest() {
        memberService.findByPk();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 조회 하면 쿼리는 수행한 만큼 생성")
    void findByNonePkTest() {
        memberService.findByNonePk();
    }

    @AfterEach
    void clean() {
        memberService.deleteAll();
    }
}
