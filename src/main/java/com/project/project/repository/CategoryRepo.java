package com.project.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.project.dto.CategoryDto;
import com.project.project.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	
	@Query("select c from Category c where c.is_activated=true and c.is_deleted=false")
	List<Category> findAllByActivated();
	
	
//customer
	@Query("select new com.project.project.dto.CategoryDto(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id " + "where c.is_activated = true and c.is_deleted = false 	group by c.id")
    List<CategoryDto> getCategoryAndProduct();
	
	@Query(value = "select new com.project.project.dto.CategoryDto(c.id, c.name, count(p.category.id)) " +
            "from Category c left join Product p on c.id = p.category.id " +
            "where c.is_activated = true and c.is_deleted = false " +
            "group by c.id ")
    List<CategoryDto> getCategoriesAndSize();

}
