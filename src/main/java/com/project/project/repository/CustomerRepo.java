package com.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.project.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
	 Customer findByUsername(String username);
	 
}
