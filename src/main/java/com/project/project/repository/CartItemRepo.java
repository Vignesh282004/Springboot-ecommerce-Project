package com.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.project.model.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {
	
	   
	   
	
}
