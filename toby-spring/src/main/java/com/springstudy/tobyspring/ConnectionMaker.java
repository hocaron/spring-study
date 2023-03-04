package com.springstudy.tobyspring;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

	Connection makeConnection() throws ClassNotFoundException, SQLException;
}
