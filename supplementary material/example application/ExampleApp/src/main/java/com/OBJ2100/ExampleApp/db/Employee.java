package com.OBJ2100.ExampleApp.db;

public class Employee {
	private int id;
	private String lastName, firstName, department, email;
	private double salary;


	public Employee(String lastName, String firstName, String department, double salary, String email) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.department = department;
		this.salary = salary;
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
