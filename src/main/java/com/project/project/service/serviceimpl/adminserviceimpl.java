package com.project.project.service.serviceimpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project.dto.AdminDto;
import com.project.project.model.Admin;
import com.project.project.repository.AdminRepo;
import com.project.project.repository.RoleRepo;
import com.project.project.service.AdminService;

@Service
public class adminserviceimpl implements AdminService {

	
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public Admin findByUsername(String username) {
        return adminRepo.findByUsername(username);
    }

    @Override
    public Admin saveAdmin(AdminDto adminDto) {
        Admin admin  = new Admin();
        admin.setFirstname(adminDto.getFirstname());
        admin.setLastname(adminDto.getLastname());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Arrays.asList(roleRepo.findByName("ADMIN")));
        return adminRepo.save(admin);
      //  throw new UnsupportedOperationException("Unimplemented method 'saveAdmin'");
    }
    

}
