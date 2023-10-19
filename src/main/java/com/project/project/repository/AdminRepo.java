package com.project.project.repository;

//import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.project.model.Admin;
public interface AdminRepo extends JpaRepository<Admin,Long> {
     
    Admin findByUsername(String username);

   // com.project.project.model.Admin save(com.project.project.model.Admin admin);
}
