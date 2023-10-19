package com.project.project.service;

import com.project.project.dto.CustomerDto;
import com.project.project.model.Customer;

public interface CustomerService {

    Customer findByUsername(String username);
	CustomerDto save(CustomerDto customerDto);
	Customer saveInfor(Customer customer);
	 CustomerDto getCustomer(String username);
	 Customer update(CustomerDto customerDto);

	    Customer changePass(CustomerDto customerDto);
}
