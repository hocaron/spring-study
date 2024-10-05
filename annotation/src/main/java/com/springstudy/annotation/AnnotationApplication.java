package com.springstudy.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @see Configuration configuration <br>
 * 스프링 부트의 Application Context 설정을 자동으로 수행 <br>
 * META-INF/spring.factories 자동등록 <br>
 *
 * @see EnableAutoConfiguration auto-configuration <br>
 * 필요한 부분만 auto configure <br>
 *
 * @see ComponentScan component scanning <br>
 * 어노테이션이 붙은 클래스의 위치를 기반으로 Bean을 찾아 Spring Container에 등록 <br>
 */
@SpringBootApplication
public class AnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnotationApplication.class, args);
	}

}
