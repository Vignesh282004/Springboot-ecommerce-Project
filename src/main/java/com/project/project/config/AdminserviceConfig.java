package com.project.project.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.project.model.Admin;
import com.project.project.repository.AdminRepo;

public class AdminserviceConfig implements UserDetailsService {

	@Autowired
	private AdminRepo adminRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin  = adminRepo.findByUsername(username);
		if(admin == null){
		throw new UsernameNotFoundException("User Not Found");
		}
		
		return new User(admin.getUsername(),admin.getPassword(),admin.getRoles().stream().map(roles->new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList()));
	}

	
}
