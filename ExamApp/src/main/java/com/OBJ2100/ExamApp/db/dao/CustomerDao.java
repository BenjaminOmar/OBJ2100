package com.OBJ2100.ExamApp.db.dao;

import java.util.List;

import com.OBJ2100.ExamApp.entities.Customer;

public interface CustomerDao {
	List<Customer> getAll();
	List<Customer> getByCity(String city);
	List<Customer> getByState(String state); 
	List<Customer> getMatchingCustomers(String city, String state);
}
