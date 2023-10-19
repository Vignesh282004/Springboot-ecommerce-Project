package com.project.project.dto;

import com.project.project.model.Category;

public class ProductDto {

	private long id;
	private String name;
	private String decription;
	private double costprice;
	private double saleprice;
	private int currentQuantity;
	private String image;
	private Category category;
	private Boolean activated;
	private Boolean deleted;
	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDto(long id, String name, String decription, double costprice, double saleprice, int currentQuantity,
			String image, Category category, Boolean activated, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.decription = decription;
		this.costprice = costprice;
		this.saleprice = saleprice;
		this.currentQuantity = currentQuantity;
		this.image = image;
		this.category = category;
		this.activated = activated;
		this.deleted = deleted;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public double getCostprice() {
		return costprice;
	}
	public void setCostprice(double costprice) {
		this.costprice = costprice;
	}
	public double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(double saleprice) {
		this.saleprice = saleprice;
	}
	public int getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = true;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = false;
	}
	
	
}
