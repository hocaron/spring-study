package com.spring.boilerplate.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QueryDslConfig {

	@PersistenceContext(unitName = "accountEntityManager")
	private EntityManager accountEntityManager;

	@PersistenceContext(unitName = "userEntityManager")
	private EntityManager userEntityManager;

	@Bean
	public JPAQueryFactory accountQueryFactory() {
		return new JPAQueryFactory(accountEntityManager);
	}

	@Bean
	public JPAQueryFactory userQueryFactory() {
		return new JPAQueryFactory(userEntityManager);
	}

}
