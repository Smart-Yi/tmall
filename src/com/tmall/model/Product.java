package com.tmall.model;

import java.util.Date;
import java.util.List;

public class Product {
	private int id ;
	private String name ;
	private String subTitle;
	private float orignalPrice;
	private float promotePrice;
	private int   stock;
	private int   cid ;
	private Date createDate ;
	//many-to-one 
	private Category category ;
	private ProductImage	   firstProductImage;
	private List<ProductImage> productImage ;
	private List<ProductImage> productSingleImage;
	private List<ProductImage> productDetailImage;
    private int reviewCount;
    private int saleCount;
	
	
	
	
	
	
	
	
	public List<ProductImage> getProductDetailImage() {
		return productDetailImage;
	}
	public void setProductDetailImage(List<ProductImage> productDetailImage) {
		this.productDetailImage = productDetailImage;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public ProductImage getFirstProductImage() {
		return firstProductImage;
	}
	public void setFirstProductImage(ProductImage firstProductImage) {
		this.firstProductImage = firstProductImage;
	}
	public List<ProductImage> getProductImage() {
		return productImage;
	}
	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
	}
	public List<ProductImage> getProductSingleImage() {
		return productSingleImage;
	}
	public void setProductSingleImage(List<ProductImage> productSingleImage) {
		this.productSingleImage = productSingleImage;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
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
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public float getOrignalPrice() {
		return orignalPrice;
	}
	public void setOrignalPrice(float orignalPrice) {
		this.orignalPrice = orignalPrice;
	}
	public float getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
