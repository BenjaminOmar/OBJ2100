package com.OBJ2100.ExamApp.entities;


public interface EntityBuilder<T> {

	/**
	 * Returns the entity being built.
	 * 
	 * @return an instance of the specific entity
	 */
	T build();
	
	/**
	 * Checks whether values used when building are valid.
	 * 
	 * @throws IllegalStateException
	 */
	void validate() throws IllegalStateException;
}
