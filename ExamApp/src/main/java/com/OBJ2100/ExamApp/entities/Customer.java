package com.OBJ2100.ExamApp.entities;

import java.util.StringJoiner;

public class Customer {
	private int customerNumber;
	private String customerName;
	private String contactLastName;
	private String contactFirstName;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private int salesRepEmployeeNumber;
	private double creditLimit;
	
	private Customer(Builder builder) {
		this.customerNumber = builder.customerNumber;
		this.customerName = builder.customerName;
		this.contactLastName = builder.contactLastName;
		this.contactFirstName = builder.contactFirstName;
		this.phone = builder.phone;
		this.addressLine1 = builder.addressLine1;
		this.addressLine2 = builder.addressLine2;
		this.city = builder.city;
		this.state = builder.state;
		this.postalCode = builder.postalCode;
		this.country = builder.country;
		this.salesRepEmployeeNumber = builder.salesRepEmployeeNumber;
		this.creditLimit = builder.creditLimit;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	public int getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public static class Builder implements EntityBuilder<Customer> {
		private int customerNumber;
		private String customerName;
		private String contactLastName;
		private String contactFirstName;
		private String phone;
		private String addressLine1;
		private String addressLine2;
		private String city;
		private String state;
		private String postalCode;
		private String country;
		private int salesRepEmployeeNumber;
		private double creditLimit;
		
		public Builder() {
			
		}
		
		public Builder(int customerNumber) {
			this.customerNumber = customerNumber;
		}
		
		public Customer build() {
			return new Customer(this);
		}
		
		public Builder customerNumber(int customerNumber) {
			this.customerNumber = customerNumber;
			return this;
		}
		
		public Builder customerName(String customerName) {
			this.customerName = customerName;
			return this;
		}
		
		public Builder contactLastName(String contactLastName) {
			this.contactLastName = contactLastName;
			return this;
		}
		
		public Builder contactFirstName(String contactFirstName) {
			this.contactFirstName = contactFirstName;
			return this;
		}
		
		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}
		
		public Builder addressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
			return this;
		}
		
		public Builder addressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
			return this;
		}
		
		public Builder city(String city) {
			this.city = city;
			return this;
		}
		
		public Builder state(String state) {
			this.state = state;
			return this;
		}
		
		public Builder postalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		
		public Builder country(String country) {
			this.country = country;
			return this;
		}
		
		public Builder salesRepEmployeeNumber(int salesRepEmployeeNumber) {
			this.salesRepEmployeeNumber = salesRepEmployeeNumber;
			return this;
		}
		
		public Builder creditLimit(double creditLimit) {
			this.creditLimit = creditLimit;
			return this;
		}
		
		public void validate() throws IllegalStateException {
			StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
			
			if (customerNumber < 0) {
				sj.add("Customer number cannot be negative.");
			}
			
			if (customerName == null) {
				sj.add("Customer name cannot be null.");
			}
			
			if (contactLastName == null) {
				sj.add("Contact last name cannot be null.");
			}
			
			if (contactFirstName == null) {
				sj.add("Contact first name cannot be null.");
			}
			
			if (phone == null) {
				sj.add("Phone cannot be null.");
			}
			
			if (addressLine1 == null) {
				sj.add("Address line 1 cannot be null.");
			}
			
			if (city == null) {
				sj.add("City cannot be null.");
			}
			
			if (country == null) {
				sj.add("Country cannot be null.");
			}
			
			if (sj.length() > 0) {
				throw new IllegalStateException(sj.toString());
			}
		}
	}
}
