package com.spring.boilerplate.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {

	@PersistenceContext(unitName = "accountEntityManager")
	private EntityManager accountEntityManager;

	@PersistenceContext(unitName = "memberEntityManager")
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
