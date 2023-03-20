package com.springstudy.tobyspring;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

	private ConnectionMaker connectionMaker;
	private User user1;
	private User user2;

	@BeforeEach
	public void setUp() throws SQLException, ClassNotFoundException {

		connectionMaker = new SimpleConnectionMaker();

		UserDaoDeleteAll userDaoDeleteAll = new UserDaoDeleteAll(connectionMaker);
		userDaoDeleteAll.deleteAll();

		user1 = new User("id1", "이름1", "password1");
		user2 = new User("id2", "이름2", "password2");
	}

	@Test
	@DisplayName("insert")
	public void addAndGet() throws SQLException, ClassNotFoundException {

		UserDaoAdd userDaoAdd = new UserDaoAdd(connectionMaker);
		userDaoAdd.add(user1);

		UserDaoGet userDaoGet = new UserDaoGet(connectionMaker);

		User result = userDaoGet.get(user1.getId());
		assertThat(user1.getPassword()).isEqualTo(result.getPassword());
	}

	@Test
	public void count() throws SQLException, ClassNotFoundException {

		UserDaoGetCount userDaoGetCount = new UserDaoGetCount(connectionMaker);

		UserDaoAdd userDaoAdd = new UserDaoAdd(connectionMaker);
		userDaoAdd.add(user1);

		assertThat(userDaoGetCount.getCount()).isEqualTo(1);

		userDaoAdd.add(user2);
		assertThat(userDaoGetCount.getCount()).isEqualTo(2);
	}

	@Test
	public void getUserFailure() throws SQLException, ClassNotFoundException {

		UserDaoGetCount userDaoGetCount = new UserDaoGetCount(connectionMaker);
		assertThat(userDaoGetCount.getCount()).isEqualTo(0);

		UserDaoGet userDaoGet = new UserDaoGet(connectionMaker);

		UserDaoAdd userDaoAdd = new UserDaoAdd(connectionMaker);
		userDaoAdd.add(user1);

		assertThrows(IllegalAccessError.class, () -> {
			userDaoGet.get("id10");
		});
	}
}
