package com.project.project.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project.model.City;
import com.project.project.repository.CityPrepo;
import com.project.project.service.Cityservice;

@Service
public class cityservimpl implements Cityservice {

	@Autowired
	private CityPrepo prepo;
	@Override
	public List<City> findAll() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

	
}
