package com.OBJ2100.ExamApp.db.dao.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoImpl<T> {
	
	protected Connection connection;
	
	protected DaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	abstract protected T extractEntity(ResultSet rs) throws SQLException;
}
