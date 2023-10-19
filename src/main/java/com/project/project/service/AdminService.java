package com.project.project.service;
import com.project.project.dto.AdminDto;
import com.project.project.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);
Admin saveAdmin(AdminDto adminDto);
    
}
