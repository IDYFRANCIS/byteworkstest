package com.francis.byteworkstest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.model.Developer;


/**
 * Developer's Repository
 * @author Francis
 *
 */
@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long>{

	
	public Developer findById(long id);
	
	// find developer account by user developerCode
	public Developer findByDeveloperCode(String developerCode);

}
