package com.springstudy.tobyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll extends UserDao {

	private ConnectionMaker connectionMaker;

	public UserDaoDeleteAll(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	protected PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("delete from users");
		return ps;
	}

	public void deleteAll() throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = connectionMaker.makeConnection();
			ps = makeStatement(c);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
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
