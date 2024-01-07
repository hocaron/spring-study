package com.spring.boilerplate.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
	basePackages = "com.spring.boilerplate.repository.account",
	entityManagerFactoryRef = "accountEntityManagerFactory",
	transactionManagerRef = "accountTransactionManager"
)
@RequiredArgsConstructor
public class AccountDataSourceConfig {

	private final Environment env;

	@Bean
	@ConfigurationProperties("spring.account-datasource")
	public DataSource accountDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(accountDataSource());

		// Entity package
		em.setPackagesToScan("com.spring.boilerplate.entity.account");
		// Query DSL
		em.setPersistenceUnitName("accountEntityManager");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		// Hibernate config
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
		properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
		properties.put("hibernate.generate_ddl", env.getProperty("spring.jpa.properties.hibernate.generate_ddl"));
		properties.put("hibernate.open_in_view", env.getProperty("spring.jpa.properties.hibernate.open_in_view"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	public PlatformTransactionManager accountTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(accountEntityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public JdbcTemplate accountJdbcTemplate() {
		return new JdbcTemplate(accountDataSource());
	}
}
