package com.spring.boilerplate.service;

import com.spring.boilerplate.entity.account.Account;
import com.spring.boilerplate.entity.user.User;
import com.spring.boilerplate.querydsl.account.CustomAccountRepository;
import com.spring.boilerplate.repository.account.AccountRepository;
import com.spring.boilerplate.util.EntityManagerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;
	private final CustomAccountRepository customAccountRepository;

	@Transactional
	public void create(User user) {
		accountRepository.save(Account.of(String.valueOf(createRandomAccount()), user));
	}

	@Transactional
	public void update(long userId) {
		var account = accountRepository.findByUserId(userId)
				.orElseThrow();
		account.updateAccount("changeAccount");
	}

	@Transactional
	public Object getDefaultTrxName() {

		return EntityManagerUtils.getEntityManagerName();
	}

	@Transactional("accountTransactionManager")
	public Object getTrxName() {

		return EntityManagerUtils.getEntityManagerName();
	}

	@Transactional
	public List<Account> getAll() {
		var temp =  accountRepository.findAll();

		return temp;
	}

	public String getById(Long id) {
		return customAccountRepository.retrieveById(id);
	}

	private long createRandomAccount() {
		Random random = new Random();
		return Math.abs(random.nextLong()) % 10000000000L;
	}
}
