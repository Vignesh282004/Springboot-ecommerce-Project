package com.project.project.service.serviceimpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.project.dto.CustomerDto;
import com.project.project.model.Customer;
import com.project.project.repository.CustomerRepo;
import com.project.project.repository.RoleRepo;
import com.project.project.service.CustomerService;

@Service
@Component
public class Customerservimpl implements CustomerService {

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public CustomerDto save(CustomerDto customerDto) {
	Customer customer = new Customer();
	customer.setFirstName(customerDto.getFirstName());
	customer.setLastName(customerDto.getLastName());
	customer.setUsername(customerDto.getUsername());
	customer.setPassword(customerDto.getPassword());
	customer.setRoles(Arrays.asList(roleRepo.findByName("CUSTOMER")));
	Customer customerSave = customerRepo.save(customer);
	return mappeDto(customerSave); 
	}

	
	public CustomerDto mappeDto(Customer customer)
	{
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setUsername(customer.getUsername());
		customerDto.setPassword(customer.getPassword());
		return customerDto;
	}


	@Override
	public Customer findByUsername(String username) {
		return customerRepo.findByUsername(username);
	}


	@Override
	public Customer saveInfor(Customer customer) {
		Customer customer1 = customerRepo.findByUsername(customer.getUsername());
        customer1.setAddress(customer.getAddress());
        customer1.setCity(customer.getCity());
        customer1.setCountry(customer.getCountry());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        return customerRepo.save(customer1);
		}


	@Override
	public CustomerDto getCustomer(String username) {
		 CustomerDto customerDto = new CustomerDto();
	        Customer customer = customerRepo.findByUsername(username);
	        customerDto.setFirstName(customer.getFirstName());
	        customerDto.setLastName(customer.getLastName());
	        customerDto.setUsername(customer.getUsername());
	        customerDto.setPassword(customer.getPassword());
	        customerDto.setAddress(customer.getAddress());
	        customerDto.setPhoneNumber(customer.getPhoneNumber());
	        customerDto.setCity(customer.getCity());
	        customerDto.setCountry(customer.getCountry());
	        return customerDto;
	}


	@Override
	public Customer update(CustomerDto dto) {
		   Customer customer = customerRepo.findByUsername(dto.getUsername());
	        customer.setAddress(dto.getAddress());
	        customer.setCity(dto.getCity());
	        customer.setCountry(dto.getCountry());
	        customer.setPhoneNumber(dto.getPhoneNumber());
	        return customerRepo.save(customer);
	}


	@Override
	public Customer changePass(CustomerDto customerDto) {
		 Customer customer = customerRepo.findByUsername(customerDto.getUsername());
	        customer.setPassword(customerDto.getPassword());
	        return customerRepo.save(customer);
	}
}
