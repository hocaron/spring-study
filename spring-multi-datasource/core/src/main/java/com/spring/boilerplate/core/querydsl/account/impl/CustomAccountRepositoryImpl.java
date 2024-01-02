package com.spring.boilerplate.core.querydsl.account.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.boilerplate.core.entity.account.Account;
import com.spring.boilerplate.core.entity.account.QAccount;
import com.spring.boilerplate.core.querydsl.account.CustomAccountRepository;

@Repository
public class CustomAccountRepositoryImpl extends AbstractAccountQuerydslSupport implements CustomAccountRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final QAccount account = QAccount.account1;

	public CustomAccountRepositoryImpl(@Qualifier("accountQueryFactory") JPAQueryFactory queryFactory) {
		super(Account.class);
		this.jpaQueryFactory = queryFactory;
	}

	@Override
	public String retrieveById(Long id) {
		return jpaQueryFactory.select(account.account)
			.from(account)
			.where(account.id.eq(1L))
			.fetchOne();
	}

}
