package com.spring.boilerplate.core.querydsl.account.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AbstractAccountQuerydslSupport extends QuerydslRepositorySupport {

	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public AbstractAccountQuerydslSupport(Class<?> domainClass) {
		super(domainClass);
	}

	@Override
	@PersistenceContext(unitName = "accountEntityManager")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
