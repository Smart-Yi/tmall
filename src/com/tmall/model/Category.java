package com.tmall.model;

import java.util.List;

public class Category {
	private int id ;
	private String name ;
	private List<Product> product ;
	private List<List<Product>>   productByRow ;
	
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public void setProductByRow(List<List<Product>> productByRow) {
		this.productByRow = productByRow;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<List<Product>> getProductByRow() {
		return productByRow;
	}
	
}
