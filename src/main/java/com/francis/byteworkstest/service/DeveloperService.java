package com.francis.byteworkstest.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.francis.byteworkstest.dto.DeveloperDto;
import com.francis.byteworkstest.dto.ServerResponse;
import com.francis.byteworkstest.model.Developer;

@Service
public interface DeveloperService {
	
	public Collection<Developer> findAll();
	
	public Developer findById(long id);
	
	public Developer findByDeveloperCode(String developerCode);
	
	public ServerResponse createDeveloper(DeveloperDto request);
	
	public ServerResponse getAllDevelopers();

}
