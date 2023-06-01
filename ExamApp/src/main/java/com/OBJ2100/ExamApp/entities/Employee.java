package com.OBJ2100.ExamApp.entities;

import java.util.StringJoiner;

public class Employee implements Entity {
	private int employeeNumber;
	private String lastName;
	private String firstName;
	private String extension;
	private String email;
	private String officeCode;
	private Integer reportsTo;
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

	public Integer getReportsTo() {
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
		private Integer reportsTo;
		private String jobTitle;
		
		public Builder() {
			
		}
		
		public Builder(int employeeNumber) {
			this.employeeNumber = employeeNumber;
		}
		

		public Employee build() throws IllegalStateException {
			validate();
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
		
		public Builder reportsTo(Integer reportsTo) {
			this.reportsTo = reportsTo;
			return this;
		}
		
		public Builder jobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
			return this;
		}
		
		public void validate() throws IllegalStateException {
			StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
			
			if (employeeNumber < 0) {
				sj.add("Employee number cannot be negative.");
			}
			
			if (lastName == null) {
				sj.add("Last name cannot be null.");
			}
			
			if (firstName == null) {
				sj.add("First name cannot be null.");
			}
			
			if (extension == null) {
				sj.add("Extension cannot be null.");
			}
			
			if (email == null) {
				sj.add("Email cannot be null.");
			}
			
			if (officeCode == null) {
				sj.add("Office code cannot be null.");
			}
			
			if (jobTitle == null) {
				sj.add("Job title cannot be null.");
			}
			
			if (sj.length() > 0) {
				throw new IllegalStateException(sj.toString());
			}
		}
	}
}
