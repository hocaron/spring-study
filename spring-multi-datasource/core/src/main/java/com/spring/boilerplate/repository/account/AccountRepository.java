package com.spring.boilerplate.repository.account;

import com.spring.boilerplate.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(Long userId);
}
