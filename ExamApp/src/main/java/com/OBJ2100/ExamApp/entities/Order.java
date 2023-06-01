package com.OBJ2100.ExamApp.entities;

import java.util.Date;

public class Order implements Entity {
	private Integer orderNumber, customerNumber;
	private String status, comments;
	private Date orderDate, requiredDate, shippedDate;
	
	private Order(Builder builder) {
		this.orderNumber = builder.orderNumber;
		this.orderDate = builder.orderDate;
		this.requiredDate = builder.requiredDate;
		this.shippedDate = builder.shippedDate;
		this.status = builder.status;
		this.comments = builder.comments;
		this.customerNumber = builder.customerNumber;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public String getComments() {
		return comments;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}
	
	public static class Builder implements EntityBuilder<Order> {
		private Integer orderNumber, customerNumber;
		private String status, comments;
		private Date orderDate, requiredDate, shippedDate;
		
		public Builder() {
			
		}
		
		public Builder(Integer orderNumber) {
			this.orderNumber = orderNumber;
		}
		
		public Order build() throws IllegalStateException {
			validate();
			return new Order(this);
		}
		
		public Builder orderNumber(Integer orderNumber) {
			this.orderNumber = orderNumber;
			return this;
		}
		
		public Builder orderDate(Date orderDate) {
			this.orderDate = orderDate;
			return this;
		}
		
		public Builder requiredDate(Date requiredDate) {
			this.requiredDate = requiredDate;
			return this;
		}
		
		public Builder shippedDate(Date shippedDate) {
			this.shippedDate = shippedDate;
			return this;
		}
		
		public Builder status(String status) {
			this.status = status;
			return this;
		}
		
		public Builder comments(String comments) {
			this.comments = comments;
			return this;
		}
		
		public Builder customerNumber(Integer customerNumber) {
			this.customerNumber = customerNumber;
			return this;
		}

		@Override
		public void validate() throws IllegalStateException {
			// TODO
		}
	}
}
