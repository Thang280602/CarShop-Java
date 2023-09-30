package com.otothang.models;

public class SearchModel {
	private String categoryName;
	private String productPrice;
	private String productAddress;
	private String model;
	private String sortId;
	
	public SearchModel() {
	}
	
	public SearchModel(String categoryName, String productPrice, String productAddress, String model, String sortId) {
		super();
		this.categoryName = categoryName;
		this.productPrice = productPrice;
		this.productAddress = productAddress;
		this.model = model;
		this.sortId = sortId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductAddress() {
		return productAddress;
	}
	public void setProductAddress(String productAddress) {
		this.productAddress = productAddress;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSortId() {
		return sortId;
	}
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return "SearchModel [categoryName=" + categoryName + ", productPrice=" + productPrice + ", productAddress="
				+ productAddress + ", model=" + model + ", sortId=" + sortId + "]";
	}
	
}
