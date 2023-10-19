package com.project.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface PageandSort<Product,Id>  extends CrudRepository<com.project.project.model.Product, Long>{

	Iterable<com.project.project.model.Product> findAll(Sort sort);
	
	Page<com.project.project.model.Product> findAll(Pageable pageable);
}
