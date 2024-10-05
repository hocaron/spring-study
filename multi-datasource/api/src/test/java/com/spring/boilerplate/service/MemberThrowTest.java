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
import static com.spring.boilerplate.service.AccountService.UPDATED_ACCOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles("test")
public class MemberThrowTest {

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

    @DisplayName("동일한 엔티티 매니저가 수행해서, 둘다 롤백된다.")
    @Test
    void updateAndThrow() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> memberService.updateAndThrow(NEW_EMAIL));

        var member = memberRepository.findById(memberId).orElseThrow();
        var account = accountRepository.findById(accountId).orElseThrow();
        assertThat(member.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(account.getAccount()).isEqualTo(NEW_ACCOUNT);
    }

    @DisplayName("내부 트랜잭션은 account 엔티티 매니저가 수행해서, member 만 롤백된다.")
    @Test
    void updateWithSpecificEntityManagerAndThrow() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> memberService.updateWithSpecificEntityManagerAndThrow(NEW_EMAIL));

        var member = memberRepository.findById(memberId).orElseThrow();
        var account = accountRepository.findById(accountId).orElseThrow();
        assertThat(member.getEmail()).isEqualTo(NEW_EMAIL);
        assertThat(account.getAccount()).isEqualTo(UPDATED_ACCOUNT);
    }

    @AfterEach
    void setDown() {
        memberRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
