package com.spring.boilerplate.core.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boilerplate.core.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
