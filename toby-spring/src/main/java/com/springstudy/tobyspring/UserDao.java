package com.springstudy.tobyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract public class UserDao {

	private ConnectionMaker connectionMaker;

	abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
}
