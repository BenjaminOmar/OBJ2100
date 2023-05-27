package com.OBJ2100.ExamApp.db.entities;

import com.OBJ2100.ExamApp.db.EntityBuilder;

public class Employee {
	private int employeeNumber;
	private String lastName;
	private String firstName;
	private String extension;
	private String email;
	private String officeCode;
	private int reportsTo;
	private String jobTitle;

	private Employee(Builder builder) {
		this.employeeNumber = builder.employeeNumber;
		this.lastName = builder.lastName;
		this.firstName = builder.firstName;
		this.extension = builder.extension;
		this.email = builder.email;
		this.officeCode = builder.officeCode;
		this.reportsTo = builder.reportsTo;
		this.jobTitle = builder.jobTitle;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public String getExtension() {
		return extension;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public String getJobTitle() {
		return jobTitle;
	}
	
	public static class Builder implements EntityBuilder<Employee> {
		private int employeeNumber;
		private String lastName;
		private String firstName;
		private String extension;
		private String email;
		private String officeCode;
		private int reportsTo;
		private String jobTitle;
		
		public Builder() {
			
		}
		
		public Builder(int employeeNumber) {
			this.employeeNumber = employeeNumber;
		}
		

		public Employee build() {
			return new Employee(this);
		}
		
		public Builder employeeNumber(int employeeNumber) {
			this.employeeNumber = employeeNumber;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder extension(String extension) {
			this.extension = extension;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder officeCode(String officeCode) {
			this.officeCode = officeCode;
			return this;
		}
		
		public Builder reportsTo(int reportsTo) {
			this.reportsTo = reportsTo;
			return this;
		}
		
		public Builder jobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
			return this;
		}
	}
}
