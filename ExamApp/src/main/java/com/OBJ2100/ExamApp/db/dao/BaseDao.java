package com.OBJ2100.ExamApp.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class BaseDao<T> implements Dao<T> {
	protected DataSource source;
	
	public BaseDao(DataSource source) {
		this.source = source;
	}
	
	public DataSource getDataSource() {
		return source;
	}
	
	protected abstract T extractEntity(ResultSet rs) throws SQLException;
}
