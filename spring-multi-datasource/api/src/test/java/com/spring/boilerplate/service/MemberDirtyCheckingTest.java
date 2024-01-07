package com.spring.boilerplate.service;

import com.spring.boilerplate.entity.account.Account;
import com.spring.boilerplate.entity.member.Member;
import com.spring.boilerplate.repository.account.AccountRepository;
import com.spring.boilerplate.repository.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.spring.boilerplate.service.AccountDirtyCheckingTest.NEW_ACCOUNT;
import static com.spring.boilerplate.service.AccountDirtyCheckingTest.NEW_EMAIL;
import static com.spring.boilerplate.service.AccountService.INNER_UPDATED_EMAIL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class MemberDirtyCheckingTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AccountRepository accountRepository;

    private Long memberId;

    @BeforeEach
    void setUp() {
        memberId = memberRepository.save(Member.from(NEW_EMAIL)).getId();
        accountRepository.save(Account.of(NEW_ACCOUNT, memberId)).getId();
    }

    @DisplayName("member 엔티티 매니저가 수행하는 내부 트랜잭션에서 member 더티체킹이 동작한다.")
    @Test
    void dirtyCheckingWithPrimaryEntityManagerTest() {
        memberService.update(NEW_EMAIL);

        var member = memberRepository.findById(memberId).get();
        assertThat(member.getEmail()).isEqualTo(INNER_UPDATED_EMAIL);
    }

    @DisplayName("account 엔티티 매니저가 수행하는 내부 트랜잭션에서 member 더티체킹이 동작한다.")
    @Test
    void dirtyCheckingWithAccountEntityManagerTest() {
        memberService.updateWithSpecificEntityManager(NEW_EMAIL);

        var member = memberRepository.findById(memberId).get();
        assertThat(member.getEmail()).isEqualTo(INNER_UPDATED_EMAIL);
    }

    @AfterEach
    void setDown() {
        memberRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
