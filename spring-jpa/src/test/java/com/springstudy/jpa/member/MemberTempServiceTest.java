package com.springstudy.jpa.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberTempServiceTest {

    @Autowired
    private TempMemberRepository tempMemberRepository;

    @Autowired
    private MemberTempService memberTempService;

    @BeforeEach
    void setUp() {
        TempMember tempMember = TempMember.of("test1");
        tempMemberRepository.save(tempMember);
        org.assertj.core.api.Assertions.assertThat(tempMemberRepository.findByNickname("test1").orElseThrow().getNickname()).isEqualTo("test1");
    }

    @Test
    void test2() {
        TempMember temp = memberTempService.temp();
    }

//    @Test
//    void test3() {
//        TempMember temp = tempMemberRepository.findByNickname("test1").orElseThrow();
//        org.assertj.core.api.Assertions.assertThat(temp.getNickname()).isEqualTo("test1");
//        temp = tempMemberRepository.findByNickname("test").orElseThrow();
//        org.assertj.core.api.Assertions.assertThat(temp.getNickname()).isEqualTo("test");
//    }
}
