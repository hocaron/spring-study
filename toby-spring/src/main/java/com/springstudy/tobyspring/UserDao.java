package com.springstudy.tobyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class UserDao {

	abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
}
