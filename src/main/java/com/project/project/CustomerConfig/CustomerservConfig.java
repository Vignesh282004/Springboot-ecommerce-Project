package com.project.project.CustomerConfig;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.project.model.Customer;
import com.project.project.repository.CustomerRepo;

public class CustomerservConfig implements UserDetailsService {
	@Autowired
	private CustomerRepo customerRepo;
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Customer customer = customerRepo.findByUsername(username);
	        if(customer == null){
	            throw new UsernameNotFoundException("Could not find username");
	        }
			return new User(customer.getUsername(), customer.getPassword(),customer.getRoles().stream().map(roles-> new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList()));	        
	    }
}