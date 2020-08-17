package com.freemarkerdemo.model;

public class Product {

	private String url;
	private String name;
	private double price;
	
	public Product() {
		super();
	}

	public Product(String url,String name, double price) {
		super();
		this.url = url;
		this.name = name;
		this.price = price;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [url=" + url + ", name=" + name + ", price=" + price + "]";
	}
	
}
