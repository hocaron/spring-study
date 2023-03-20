package com.springstudy.tobyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoAdd {

	private ConnectionMaker connectionMaker;

	public UserDaoAdd(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	protected PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
		return ps;
	}

	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = connectionMaker.makeConnection();
			ps = makeStatement(c);

			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {

				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {

				}
			}
		}
	}
}
