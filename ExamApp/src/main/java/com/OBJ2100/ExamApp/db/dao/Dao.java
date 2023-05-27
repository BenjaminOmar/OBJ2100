package com.OBJ2100.ExamApp.db.dao;

import java.util.List;
import java.sql.SQLException;

/**
 * @see <a href="https://www.baeldung.com/java-dao-pattern">The DAO Pattern in Java</a>
 */
public interface Dao<T> {
	
	T get(Number id) throws SQLException;
	
	List<T> getAll() throws SQLException;
	
	void add(T t) throws SQLException;
	
	void update(T t, Object[] params) throws SQLException;
	
	void delete(T t) throws SQLException;
}
