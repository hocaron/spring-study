package com.springstudy.tobyspring;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {

	private UserDao dao;
	private User user1;
	private User user2;

	@BeforeEach
	public void setUp() {

		dao = new DaoFactory().userDao();
		user1 = new User("id1", "이름1", "password1");
		user2 = new User("id2", "이름2", "password2");
	}

	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

		dao.deleteAll();

		dao.add(user1);

		User result = dao.get(user1.getId());
		assertThat(user1.getPassword()).isEqualTo(result.getPassword());
	}

	@Test
	public void count() throws SQLException, ClassNotFoundException {

		dao.deleteAll();
		assertThat(dao.getCount()).isEqualTo(0);

		dao.add(user1);
		assertThat(dao.getCount()).isEqualTo(1);

		dao.add(user2);
		assertThat(dao.getCount()).isEqualTo(2);
	}

	@Test
	public void getUserFailure() throws SQLException, ClassNotFoundException {

		dao.deleteAll();

		dao.deleteAll();
		assertThat(dao.getCount()).isEqualTo(0);

		dao.add(user1);
		assertThrows(IllegalAccessError.class, () -> {
			dao.get("id10");
		});
	}
}
