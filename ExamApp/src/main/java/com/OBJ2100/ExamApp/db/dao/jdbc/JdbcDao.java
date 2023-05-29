package com.OBJ2100.ExamApp.db.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcDao<T> {
	
	protected Connection connection;
	
	protected JdbcDao(Connection connection) {
		this.connection = connection;
	}
	
	abstract protected T extractEntity(ResultSet rs) throws SQLException;
}
