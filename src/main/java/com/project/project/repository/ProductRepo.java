package com.project.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.project.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {	

	@Query(value = "select *from Product p where p.name like %:keyword% or p.decription like %:keyword%",nativeQuery = true)
	List<Product> findByKeyword(@Param("keyword")String keyword);
	
	
	@Query("select p from Product p where p.is_activated =true and p.is_deleted = false")
	List<Product> getAllProducts();

	
	@Query("select p from Product p inner join Category c on c.id = p.category.id where c.id = ?1 and p.is_deleted = false and p.is_activated = true")
    List<Product> getProductsInCategory(Long categoryId);
	
	@Query("select p from Product p where p.is_activated =true and p.is_deleted = false")
	List<Product> listViewProducts();


	  @Query(value = "select *from product p inner join categories c on c.category_id = p.category_id where p.category_id = ?1", nativeQuery = true)
	    List<Product> getRelatedProducts(Long categoryId);
	 
	
	  @Query(value = "select " +
	            "p.product_id, p.name, p.decription, p.current_quantity, p.costprice, p.category_id, p.saleprice, p.image, p.is_activated, p.is_deleted " +
	            "from product p where p.is_deleted = false and p.is_activated = true order by p.costprice desc", nativeQuery = true)
	    List<Product> filterHighProducts();

	    @Query(value = "select " +
	            "p.product_id, p.name, p.decription, p.current_quantity, p.costprice, p.category_id, p.saleprice, p.image, p.is_activated, p.is_deleted " +
	            "from product p where p.is_deleted = false and p.is_activated = true order by p.costprice asc", nativeQuery = true)
	    List<Product> filterLowerProducts();
	
	    
	    @Query(value = "select " + "p.product_id, p.name, p.decription, p.current_quantity, p.costprice, p.category_id, p.saleprice, p.image, p.is_activated, p.is_deleted " +"from product p where p.is_activated = true and p.is_deleted = false order by rand()", nativeQuery = true)
	    List<Product> randomProduct();
}
