package com.spring.boilerplate.core.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

@Configuration
@PropertySources({
	@PropertySource("classpath:application.yml"),
	@PropertySource(value = "classpath:application-${spring.profiles.active}.yml")
})
@EnableJpaRepositories(
	basePackages = "com.spring.boilerplate.core.repository.user",
	entityManagerFactoryRef = "userEntityManagerFactory",
	transactionManagerRef = "userTransactionManager"
)
@RequiredArgsConstructor
public class UserDataSourceConfig {

	private final Environment env;

	@Bean
	@ConfigurationProperties("spring.user-datasource")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(userDataSource());

		// Entity package
		em.setPackagesToScan("com.spring.boilerplate.core.entity.user");
		// Query DSL
		em.setPersistenceUnitName("userEntityManager");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		// Hibernate config
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl"));
		properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.generate_ddl", env.getProperty("hibernate.generate_ddl"));
		properties.put("hibernate.open_in_view", env.getProperty("hibernate.open_in_view"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	public PlatformTransactionManager userTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(userEntityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public JdbcTemplate userJdbcTemplate() {
		return new JdbcTemplate(userDataSource());
	}

}
