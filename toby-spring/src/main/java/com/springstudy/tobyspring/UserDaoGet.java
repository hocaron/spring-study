package com.springstudy.tobyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoGet {

	private ConnectionMaker connectionMaker;

	public UserDaoGet(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	protected PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		return ps;
	}

	public User get(String id) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			c = connectionMaker.makeConnection();
			ps = makeStatement(c);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
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

		Optional.ofNullable(user).orElseThrow(IllegalAccessError::new);
		return user;
	}
}
