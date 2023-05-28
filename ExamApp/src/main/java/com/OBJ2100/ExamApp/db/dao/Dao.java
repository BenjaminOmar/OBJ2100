package com.OBJ2100.ExamApp.db.dao;

import java.util.List;
import java.sql.SQLException;

/**
 * @see <a href="https://www.baeldung.com/java-dao-pattern">The DAO Pattern in Java</a>
 */
public interface Dao<T> {
	
	void create(T t) throws SQLException;
	
	T read(Number id) throws SQLException;
	
	List<T> readAll() throws SQLException;
	
	void update(T t, Object[] params) throws SQLException;
	
	void delete(T t) throws SQLException;
}
