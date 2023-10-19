package com.project.project.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.project.dto.CategoryDto;
import com.project.project.dto.ProductDto;
import com.project.project.model.Category;

@Component
public interface CAtegoryService {

	List<Category> findAll();
	void deleteById(Long id);
	Category Update(Category category);
	Category save(Category category);
	void enableById(Long id);
	Category findById(Long id);
	List<Category> findAllByActivated();
	
	//customer
	 List<CategoryDto> getCategoryAndProduct();
	 
	 List<CategoryDto> getCategoriesAndSize();
}
