package com.OBJ2100.ExampleApp.db;

import java.sql.SQLException;
import java.util.List;

/**
 * Declaration of the methods for establishing database connection the interface
 * provides all necessary methods that should be implemented to achieve
 * successful connection to a database
 */

public interface DatabaseInterface {

	/**
	 * Open connection
	 * 
	 * @throws SQLException
	 */
	void open() throws SQLException;

	/**
	 * Close connection
	 * 
	 * @throws SQLException
	 */
	void close() throws SQLException;

	/**
	 * Test connection
	 * 
	 * @throws SQLException
	 */
	void test() throws SQLException;

	/**
	 * Method for selection of employees that meet the criteria it select employees
	 * that have salary higher than specified and from defined department
	 * 
	 * @param minSalary  represents the threshold salary for employees selection
	 * @param department from which to select employees
	 * @return it returns the list of employees that meet the criteria
	 * @throws SQLException
	 */
	List<Employee> getEmployees(double minSalary, String department) throws SQLException;

	/**
	 * Method for retrieving a list of employees from database it selects all
	 * employees without any applied filter
	 * 
	 * @return List of Employee objects
	 * @throws SQLException
	 */
	public List<Employee> getEmployees() throws SQLException;

	/**
	 * Adding employee with provided first and last name other non mandatory fields
	 * in a database are left unedited or with default values
	 * 
	 * @param firtsName first name of new employee
	 * @param LastName  last name of new employee
	 * @throws SQLException
	 */
	void addEmployee(String firtsName, String LastName) throws SQLException;

	/**
	 * Adding employee with all details
	 * 
	 * @param firtsName  first name of new employee
	 * @param LastName   last name of new employee
	 * @param department department that new employee is assigned to
	 * @param email      email of the employee
	 * @param salary     salary of the employee
	 * @throws SQLException
	 */
	void addEmployee(String firtsName, String LastName, String department, String email, double salary)
			throws SQLException;

}
