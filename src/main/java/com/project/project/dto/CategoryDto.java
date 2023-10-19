package com.project.project.dto;

public class CategoryDto {

	private Long categoryId;
	private String categoryName;
	private Long numberofProduct;
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryDto(Long categoryId, String categoryName, Long numberofProduct) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.numberofProduct = numberofProduct;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getNumberofProduct() {
		return numberofProduct;
	}
	public void setNumberofProduct(Long numberofProduct) {
		this.numberofProduct = numberofProduct;
	}
	
}
