package com.project.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "categories",uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long id;
	private String name;
	private Boolean is_activated;
	private Boolean is_deleted;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String name)
	{
		this.name = name;
		this.is_activated=true;
		this.is_deleted=false;
	}
	public Category(Long id, String name, Boolean is_activated, Boolean is_deleted) {
		this.id = id;
		this.name = name;
		this.is_activated = is_activated;
		this.is_deleted = is_deleted;
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
	public Boolean getIs_activated() {
		return is_activated;
	}
	public void setIs_activated(Boolean is_activated) {
		this.is_activated = is_activated;
	}
	public Boolean getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", is_activated=" + is_activated + ", is_deleted=" + is_deleted
				+ "]";
	}
	
	
	
	
}
