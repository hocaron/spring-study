package com.spring.boilerplate.service;

import com.spring.boilerplate.entity.member.Member;
import com.spring.boilerplate.repository.account.AccountRepository;
import com.spring.boilerplate.util.EntityManagerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;
	public static final String INNER_UPDATED_EMAIL = "innerUpdated@email.com";
	public static final String UPDATED_ACCOUNT = "updatedAccount";

	@Transactional
	public void update(Member member) {
		member.updateEmail(INNER_UPDATED_EMAIL);
		var account = accountRepository.findByMemberId(member.getId())
				.orElseThrow();
		account.updateAccount(UPDATED_ACCOUNT);
	}

	@Transactional("accountTransactionManager")
	public void updateWithAccountEntityManager(Member member) {
		member.updateEmail(INNER_UPDATED_EMAIL);
		var account = accountRepository.findByMemberId(member.getId())
				.orElseThrow();
		account.updateAccount(UPDATED_ACCOUNT);
	}

	@Transactional
	public void updateAndThrow(Member member) {
		member.updateEmail(INNER_UPDATED_EMAIL);
		var account = accountRepository.findByMemberId(member.getId())
				.orElseThrow();
		account.updateAccount(UPDATED_ACCOUNT);
		throw new RuntimeException();
	}

	@Transactional("accountTransactionManager")
	public void updateWithAccountEntityManagerAndThrow(Member member) {
		member.updateEmail(INNER_UPDATED_EMAIL);
		var account = accountRepository.findByMemberId(member.getId())
				.orElseThrow();
		account.updateAccount(UPDATED_ACCOUNT);
		throw new RuntimeException();
	}

	@Transactional
	public Object getDefaultTrxName() {

		return EntityManagerUtils.getEntityManagerName();
	}

	@Transactional("accountTransactionManager")
	public Object getTrxName() {

		return EntityManagerUtils.getEntityManagerName();
	}
}
