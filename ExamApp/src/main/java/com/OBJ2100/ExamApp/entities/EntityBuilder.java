package com.OBJ2100.ExamApp.entities;



public interface EntityBuilder<T> {
	
	T build();
	
	void validate() throws IllegalStateException;
}
