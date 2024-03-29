package com.spring.boilerplate.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MemberService memberService;

    private static final String ACCOUNT_ENTITY_MANAGER = "accountEntityManager";
    private static final String MEMBER_ENTITY_MANAGER = "memberEntityManager";

    @DisplayName("트랜잭셔널 기본 설정인 경우, primary 로 주입한 엔티티 매니저가 주입된다.")
    @Test
    void primaryEntityManagerBeanTest() {
        assertThat(accountService.getDefaultTrxName()).isEqualTo(MEMBER_ENTITY_MANAGER);
    }

    @DisplayName("트랜잭셔널 엔티티 매니저 설정한 경우, 직접 주입한 엔티티 매니저가 주입된다.")
    @Test
    void injectMemberEntityManagerBeanTest() {
        assertThat(memberService.getTrxName()).isEqualTo(MEMBER_ENTITY_MANAGER);
    }

    @DisplayName("트랜잭셔널 엔티티 매니저 설정한 경우, 직접 주입한 엔티티 매니저가 주입된다.")
    @Test
    void injectAccountEntityManagerBeanTest() {
        assertThat(accountService.getTrxName()).isEqualTo(ACCOUNT_ENTITY_MANAGER);
    }
}
