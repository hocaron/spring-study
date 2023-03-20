package com.springstudy.tobyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public ConnectionMaker makeConnection() {
		return new SimpleConnectionMaker();
	}

}
