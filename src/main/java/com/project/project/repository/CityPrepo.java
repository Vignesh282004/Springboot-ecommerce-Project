package com.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.project.model.City;

@Repository
public interface CityPrepo  extends JpaRepository<City,Long>{

	
}
