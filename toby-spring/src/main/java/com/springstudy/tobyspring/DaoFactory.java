package com.springstudy.tobyspring;

public class DaoFactory {

	private ConnectionMaker makeConnection() {
		return new SimpleConnectionMaker();
	}

	public UserDao userDao() {
		ConnectionMaker connectionMaker = makeConnection();
		UserDao userDao = new UserDao(connectionMaker);
		return userDao;
	}
}
