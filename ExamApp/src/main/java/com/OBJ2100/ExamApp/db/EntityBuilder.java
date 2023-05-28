package com.OBJ2100.ExamApp.db;



public interface EntityBuilder<T> {
	
	T build();
	
	void validate() throws IllegalStateException;
}
