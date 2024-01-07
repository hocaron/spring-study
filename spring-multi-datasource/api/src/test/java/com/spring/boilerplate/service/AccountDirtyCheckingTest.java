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

import static com.spring.boilerplate.service.AccountService.UPDATED_ACCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AccountDirtyCheckingTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AccountRepository accountRepository;

    public static final String NEW_ACCOUNT = "newAccount";
    public static final String NEW_EMAIL = "new@email.com";

    private Long memberId;
    private Long accountId;

    @BeforeEach
    void setUp() {
        memberId = memberRepository.save(Member.from(NEW_EMAIL)).getId();
        accountId = accountRepository.save(Account.of(NEW_ACCOUNT, memberId)).getId();
    }

    @DisplayName("member 엔티티 매니저가 수행하는 내부 트랜잭션에서 account 더티체킹이 동작하지 않는다.")
    @Test
    void dirtyCheckingWithPrimaryEntityManagerTest() {
        memberService.update(NEW_EMAIL);

        var account = accountRepository.findById(accountId).get();
        assertThat(account.getAccount()).isEqualTo(NEW_ACCOUNT);
    }

    @DisplayName("account 엔티티 매니저가 수행하는 내부 트랜잭션에서 account 더티체킹이 동작한다.")
    @Test
    void dirtyCheckingWithAccountEntityManagerTest() {
        memberService.updateWithSpecificEntityManager(NEW_EMAIL);

        var account = accountRepository.findById(accountId).get();
        assertThat(account.getAccount()).isEqualTo(UPDATED_ACCOUNT);
    }

    @AfterEach
    void setDown() {
        memberRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
