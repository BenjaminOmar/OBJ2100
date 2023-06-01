package com.OBJ2100.ExamApp.entities;

public class Office implements Entity {
	private String officeCode;
	private String city;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String state;
	private String country;
	private String postalCode;
	private String territory;
	
	private Office(Builder builder) {
		this.officeCode = builder.officeCode;
		this.city = builder.city;
		this.phone = builder.phone;
		this.addressLine1 = builder.addressLine1;
		this.addressLine2 = builder.addressLine2;
		this.state = builder.state;
		this.country = builder.country;
		this.postalCode = builder.postalCode;
		this.territory = builder.territory;
	}
	
	public String getOfficeCode() {
		return officeCode;
	}

	public String getCity() {
		return city;
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

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getTerritory() {
		return territory;
	}
	
	/**
	 * Implementation of the Builder Pattern for Customer.
	 * 
	 * @author 7154, 7131
	 *
	 */
	public static class Builder implements EntityBuilder<Office> {
		private String officeCode;
		private String city;
		private String phone;
		private String addressLine1;
		private String addressLine2;
		private String state;
		private String country;
		private String postalCode;
		private String territory;
		
		public Builder() {
			
		}
		
		public Builder(String officeCode) {
			this.officeCode = officeCode;
		}
		
		public Office build() throws IllegalStateException {
			validate();
			return new Office(this);
		}
		
		public Builder officeCode(String officeCode) {
			this.officeCode = officeCode;
			return this;
		}
		
		public Builder city(String city) {
			this.city = city;
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
		
		public Builder state(String state) {
			this.state = state;
			return this;
		}
		
		public Builder country(String country) {
			this.country = country;
			return this;
		}
		
		public Builder postalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		
		public Builder territory(String territory) {
			this.territory = territory;
			return this;
		}
		
		public void validate() throws IllegalStateException {
			// TODO
		}
	}
}
