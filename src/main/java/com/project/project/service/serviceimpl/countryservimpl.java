package com.project.project.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project.model.Country;
import com.project.project.repository.CountryRepo;
import com.project.project.service.CountryService;
@Service
public class countryservimpl  implements CountryService {

	@Autowired
	private CountryRepo countryRepo;
	@Override
	public List<Country> findAll() {
		// TODO Auto-generated method stub
		return countryRepo.findAll();
	}

	
}
