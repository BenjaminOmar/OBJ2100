package com.OBJ2100.ExamApp.db;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the methods for establishing database connection
 */

public interface DatabaseInterface {
	
	//	open connection
	void open() throws SQLException;
	
	// close connection
	void close() throws SQLException;
	
	// test connection
	void test() throws SQLException;
	
	// Employee
	// get list of all employees
	List<Employee> getEmployees() throws SQLException;
	
	//add employee with name
	void addEmployee(String firtsName, String LastName) throws SQLException;
	
	//add employee with all details
	void addEmployee(String firtsName, String LastName, String department, String email, double salary) throws SQLException;
	
	// Customer
	List<Customer> getCustomers() throws SQLException;
	
}
