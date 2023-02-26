package com.springstudy.tobyspring;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TobySpringApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(TobySpringApplication.class, args);

		UserDao dao = new UserDao();

		User user = new User();
		user.setId("hocaron");
		user.setName("호선우");
		user.setPassword("hocaron");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getId() + " 조회 성공");
	}
}
