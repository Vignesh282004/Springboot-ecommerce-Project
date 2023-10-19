package com.project.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.project.project.dto.ProductDto;
import com.project.project.model.Product;

public interface ProductService {

	//Admin
	List<ProductDto> findAll();
	Product saveProduct(MultipartFile imageProduct,ProductDto productDto);
	Product updateProduct(MultipartFile imageProduct,ProductDto productDto);
	void deleteById(Long id);
	void enableById(Long id);
	ProductDto getById(Long id);
	
	Page<Product> findPaginated(int pageNo,int pageSize);

	
	List<Product> getByKeyword(String keyword);
	
	//customer
	List<Product> getAllProducts();
	
	List<Product> getProductsInCategory(Long categoryId);
	
	List<ProductDto> listViewProducts();
	
	Product getProductById(Long id);
	
	 List<Product> getRelatedProducts(Long categoryId);
	 

	    List<ProductDto> filterHighProducts();

	    List<ProductDto> filterLowerProducts();
	List<ProductDto> randomProduct();

	
}
