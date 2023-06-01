package com.OBJ2100.ExamApp.entities;

public class Product implements Entity {
	private Integer quantityInStock;
	private String productCode, productName, productLine, productScale, productVendor, productDescription;
	private Double buyPrice, msrp;
	
	private Product(Builder builder) {
		this.productCode = builder.productCode;
		this.productName = builder.productName;
		this.productLine = builder.productLine;
		this.productScale = builder.productScale;
		this.productVendor = builder.productVendor;
		this.productDescription = builder.productDescription;
		this.quantityInStock = builder.quantityInStock;
		this.buyPrice = builder.buyPrice;
		this.msrp = builder.msrp;
	}
	
	public String getProductCode() {
		return productCode;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductLine() {
		return productLine;
	}

	public String getProductScale() {
		return productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public Double getMsrp() {
		return msrp;
	}

	/**
	 * Implementation of the Builder Pattern for Customer.
	 * 
	 * @author 7154, 7131
	 *
	 */
	public static class Builder implements EntityBuilder<Product> {
		private Integer quantityInStock;
		private String productCode, productName, productLine, productScale, productVendor, productDescription;
		private Double buyPrice, msrp;
		
		public Builder() {
			
		}
		
		public Builder(String productCode) {
			this.productCode = productCode;
		}
		
		public Product build() throws IllegalStateException {
			validate();
			return new Product(this);
		}
		
		public Builder productCode(String productCode) {
			this.productCode = productCode;
			return this;
		}
		
		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}
		
		public Builder productLine(String productLine) {
			this.productLine = productLine;
			return this;
		}
		
		public Builder productScale(String productScale) {
			this.productScale = productScale;
			return this;
		}
		
		public Builder productVendor(String productVendor) {
			this.productVendor = productVendor;
			return this;
		}
		
		public Builder productDescription(String productDescription) {
			this.productDescription = productDescription;
			return this;
		}
		
		public Builder quantityInStock(Integer quantityInStock) {
			this.quantityInStock = quantityInStock;
			return this;
		}
		
		public Builder buyPrice(Double buyPrice) {
			this.buyPrice = buyPrice;
			return this;
		}
		
		public Builder msrp(Double mrsp) {
			this.msrp = mrsp;
			return this;
		}

		@Override
		public void validate() throws IllegalStateException {
			// TODO
		}

	}
}
