package com.francis.byteworkstest.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.francis.byteworkstest.dto.DeveloperDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.model.Developer;

@Service
public interface DeveloperService {
	
	//Method to get all developers on the system
	public Collection<Developer> findAll();
	
	public Developer findById(long id);
	
	//Method to find developer using developer code
	public Developer findByDeveloperCode(String developerCode);
	
	//Method to create a developer account on the system
	public ServerResponse createDeveloper(DeveloperDto request);
	
	//Method to fetch all developers 
	public ServerResponse getAllDevelopers();

}
