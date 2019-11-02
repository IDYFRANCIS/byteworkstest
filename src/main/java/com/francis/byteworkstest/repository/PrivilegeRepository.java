package com.francis.byteworkstest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.francis.byteworkstest.enumType.UserPrivilageType;
import com.francis.byteworkstest.model.Privilege;


/**
 * Privilege Repository
 * @author Francis
 *
 */
@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long>{

	public Privilege findById(long id);

	// find privilege by privilege name  	
	public Privilege findByName(UserPrivilageType name);
	
}
