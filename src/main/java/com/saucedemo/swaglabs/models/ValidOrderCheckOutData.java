package com.saucedemo.swaglabs.models;

import java.util.List;

public class ValidOrderCheckOutData {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String postalCode;
	private String orderConfirmationMessage;
	private List<Product> products;

	public ValidOrderCheckOutData() {
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getOrderConfirmationMessage() {
		return orderConfirmationMessage;
	}

	public void setOrderConfirmationMessage(String orderConfirmationMessage) {
		this.orderConfirmationMessage = orderConfirmationMessage;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static class Product {
		private String name;
		private String price;

		public Product() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}
	}
}
