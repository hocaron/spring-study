package com.spring.boilerplate.service;

import com.spring.boilerplate.entity.member.Member;
import com.spring.boilerplate.repository.member.MemberRepository;
import com.spring.boilerplate.util.EntityManagerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final AccountService accountService;

	public static final String OUTER_CHANGED_EMAIL = "outerChanged@email.com";

	@Transactional
	public Member update(String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow();

		member.updateEmail(OUTER_CHANGED_EMAIL);
		accountService.update(member);
		return member;
	}

	@Transactional
	public Member updateWithSpecificEntityManager(String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow();
		member.updateEmail(OUTER_CHANGED_EMAIL);
		accountService.updateWithAccountEntityManager(member);
		return member;
	}

	@Transactional
	public Member updateAndThrow(String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow();
		member.updateEmail(OUTER_CHANGED_EMAIL);
		accountService.update(member);
		throw new RuntimeException();
	}

	@Transactional
	public Member updateWithSpecificEntityManagerAndThrow(String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow();
		member.updateEmail(OUTER_CHANGED_EMAIL);
		accountService.updateWithAccountEntityManager(member);
		throw new RuntimeException();
	}

	@Transactional
	public Member updateAndInnerThrow(String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow();
		member.updateEmail(OUTER_CHANGED_EMAIL);
		accountService.updateAndThrow(member);
		return member;
	}

	@Transactional
	public Member updateWithSpecificEntityManagerAndInnerThrow(String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow();
		member.updateEmail(OUTER_CHANGED_EMAIL);
		accountService.updateWithAccountEntityManagerAndThrow(member);
		return member;
	}

	@Transactional("memberTransactionManager")
	public String getTrxName() {
		return EntityManagerUtils.getEntityManagerName();
	}
}
