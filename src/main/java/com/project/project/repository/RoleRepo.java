package com.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.project.model.Roles;


public interface RoleRepo extends JpaRepository<Roles,Long>{
    Roles findByName(String name);
}
