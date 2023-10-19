package com.project.project.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.project.dto.CategoryDto;
import com.project.project.dto.ProductDto;
import com.project.project.model.Category;
import com.project.project.repository.CategoryRepo;
import com.project.project.service.CAtegoryService;
@Service
@Component
public class CAtegoryservimpl implements CAtegoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private CAtegoryservimpl cAtegoryservimpl;
	@Override
	public void deleteById(Long id) {
		Category category = categoryRepo.findById(id).get();
		category.setIs_deleted(true);
		category.setIs_activated(false);
		 categoryRepo.save(category);
	}

	@Override
	public Category Update(Category category) {
		Category updateCategory = null;
		try {
			 updateCategory = categoryRepo.findById(category.getId()).get();
			updateCategory.setName(category.getName());
			updateCategory.setIs_activated(true);
			updateCategory.setIs_deleted(false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return categoryRepo.save(updateCategory);
	}

	@Override
	public Category save(Category category) {
		Category category2 = new Category(category.getName());
		return categoryRepo.save(category2);
	}

	@Override
	public void enableById(Long id) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(id).get();
		category.setIs_activated(true);
		category.setIs_deleted(false);
		categoryRepo.save(category);
	}

	
@Override
	public List<Category> findAll() {
		return (List<Category>) categoryRepo.findAll();
	}


@Override
public Category findById(Long id) {
	// TODO Auto-generated method stub
	return categoryRepo.findById(id).get();
}

@Override
public List<Category> findAllByActivated() {
	return categoryRepo.findAllByActivated();
}

@Override
public List<CategoryDto> getCategoryAndProduct() {
	return categoryRepo.getCategoryAndProduct();
}

@Override
public List<CategoryDto> getCategoriesAndSize() {
	List<CategoryDto> categoryDtos = categoryRepo.getCategoriesAndSize();
	return categoryDtos;
}





	


}
