package com.project.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "product",uniqueConstraints = @UniqueConstraint(columnNames = {"name","image"}))
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	private String name;
	private String decription;
	private double costprice;
	private double saleprice;
	private int currentQuantity;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	@ManyToOne(fetch = FetchType.EAGER , cascade =  CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
	private Category category;
	private Boolean is_deleted;
	private Boolean is_activated;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(Long id, String name, String decription, double costprice, double saleprice, int currentQuantity,
			String image, Category category, Boolean is_deleted, Boolean is_activated) {
		super();
		this.id = id;
		this.name = name;
		this.decription = decription;
		this.costprice = costprice;
		this.saleprice = saleprice;
		this.currentQuantity = currentQuantity;
		this.image = image;
		this.category = category;
		this.is_deleted = is_deleted;
		this.is_activated = is_activated;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public boolean isIs_activated() {
		return is_activated;
	}

	public void setIs_activated(Boolean is_activated) {
		this.is_activated = is_activated;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", decription=" + decription + ", costprice=" + costprice
				+ ", saleprice=" + saleprice + ", currentQuantity=" + currentQuantity + ", image=" + image
				+ ", category=" + category + ", is_deleted=" + is_deleted + ", is_activated=" + is_activated + "]";
	}

	
	
}
