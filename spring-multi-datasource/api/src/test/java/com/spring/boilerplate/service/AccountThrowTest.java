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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles("test")
public class AccountThrowTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AccountRepository accountRepository;

    private Long memberId;
    private Long accountId;

    @BeforeEach
    void setUp() {
        memberId = memberRepository.save(Member.from(NEW_EMAIL)).getId();
        accountId = accountRepository.save(Account.of(NEW_ACCOUNT, memberId)).getId();
    }

    @DisplayName("내부에서 예외가 발생하여, 둘다 롤백된다.")
    @Test
    void updateAndInnerThrow() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> memberService.updateAndInnerThrow(NEW_EMAIL));

        var member = memberRepository.findById(memberId).orElseThrow();
        var account = accountRepository.findById(accountId).orElseThrow();
        assertThat(member.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(account.getAccount()).isEqualTo(NEW_ACCOUNT);
    }

    @DisplayName("내부에서 예외가 발생하여, 둘다 롤백된다.")
    @Test
    void updateWithSpecificEntityManagerAndInnerThrow() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> memberService.updateWithSpecificEntityManagerAndInnerThrow(NEW_EMAIL));

        var member = memberRepository.findById(memberId).orElseThrow();
        var account = accountRepository.findById(accountId).orElseThrow();
        assertThat(member.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(account.getAccount()).isEqualTo(NEW_ACCOUNT);
    }

    @AfterEach
    void setDown() {
        memberRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
